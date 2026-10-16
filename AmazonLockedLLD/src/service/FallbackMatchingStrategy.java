package service;


import static enums.Size.*;

import enums.Size;
import java.util.List;
import model.Locker;

public class FallbackMatchingStrategy implements LockerMatchingStrategy{

  private static final List<Size> sizeOrder = List.of(SMALL, MEDIUM, LARGE);

  @Override
  public Locker match(List<Locker> lockerList, Size size) {
    int start = sizeOrder.indexOf(size);

    for (int i=start;i<sizeOrder.size();i++) {
      for (Locker locker : lockerList) {
        if (sizeOrder.get(i) == locker.getSize() && locker.isAvailable()) {
          return locker;
        }
      }
    }

    return null;
  }
}
