package splitwiselld.model;
import java.util.*;

public class BalanceSheet {
  Map<User, Map<User, Double>> balanceMap;

  public BalanceSheet() {
    balanceMap = new HashMap<>();
  }

  private void updateAmountUser(User cur, User other, Double amount) {
    Map<User, Double> userMap = balanceMap.getOrDefault(cur, new HashMap<>());
    userMap.put(other, userMap.getOrDefault(other, (double)0) + amount);
    if(userMap.get(other) == 0) {
      userMap.remove(other);
    }
    balanceMap.put(cur, userMap);
  }

  public void payForUser(User cur, User other, Double amount) {
    updateAmountUser(cur,other,amount);
//    updateAmountUser(other, cur, -amount);
  }

  public void getFromUser(User cur, User other, Double amount) {
    updateAmountUser(cur,other,-amount);
//    updateAmountUser(other, cur, amount);
  }

  public Map<User, Map<User, Double>> getBalanceMap() {
    return Map.copyOf(balanceMap);
  }

  public void settleUp() {
    // implement later
  }
}
