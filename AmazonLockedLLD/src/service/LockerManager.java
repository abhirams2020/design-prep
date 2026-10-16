package service;

import enums.Size;
import model.AccessToken;
import model.Locker;
import java.util.*;
import model.impl.LockerFactory;
import model.impl.NumberAccessToken;

public class LockerManager {
  List<Locker> lockers;
  Map<String, AccessToken> tokenMap;
  LockerMatchingStrategy strategy;
  private static volatile LockerManager manager;

  private LockerManager(LockerMatchingStrategy strategy) {
    this.strategy = strategy;
    this.lockers = new ArrayList<>();
    this.tokenMap = new HashMap<>();
  }

  public static LockerManager getInstance() {
    if (manager == null) {
      synchronized (LockerManager.class) {
        if (manager == null) {
          manager = new LockerManager(new FallbackMatchingStrategy());
          return manager;
        }
      }
    }
    return manager;
  }

  public void addLocker(Size size) {
    lockers.add(LockerFactory.createLocker(size));
  }

  public void removeLocker(Locker locker) {
    lockers.remove(locker);
  }

  public String depositPackage(Size size) {
    Locker locker = strategy.match(lockers, size);
    if (locker == null) {
      throw new RuntimeException("No locker found for size " + size);
    }
    locker.open();
    locker.markOccupied();
    System.out.println("Put package in locker");
    locker.close();
    AccessToken token = new NumberAccessToken(locker);
    tokenMap.put(token.getCode(), token);

    return token.getCode();
  }

  public void pickupPackage(String code) throws IllegalAccessException {
    if (!tokenMap.containsKey(code)) {
      throw new IllegalAccessException("Token does not exist");
    }

    AccessToken token = tokenMap.get(code);
    if (token.isExpired()) {
      throw new IllegalAccessException("Entered token is expired");
    }

    Locker locker = token.getLocker();

    locker.open();
    locker.markAvailable();
    System.out.println("Took package from locker");
    locker.close();
  }

  public void printLockers() {
    for (Locker locker:lockers) {
      System.out.println("|" + locker.getSize() + "," + (locker.isAvailable() ? "A" : "O") + "|");
    }
  }

  public void openExpiredLockers() {
    if (tokenMap.isEmpty()) {
      return;
    }
    List<String> codes = tokenMap.keySet().stream().toList();
    for (String code : codes) {
      AccessToken token = tokenMap.get(code);
      if (token!=null && token.isExpired()) {
        token.getLocker().markAvailable();
        tokenMap.remove(code);
      }
    }
  }
}
