package AtmManagement;

import AtmManagement.model.user.User;
import java.util.concurrent.ConcurrentHashMap;

public class BalanceService {

  public static BalanceService instance = new BalanceService();
  private ConcurrentHashMap<User, Integer> balanceMap;

  private BalanceService() {
    balanceMap = new ConcurrentHashMap<>();
  }

  public int checkBalance(User user) {
    return balanceMap.getOrDefault(user, 0);
  }

  public void depositMoney(User user, int money) {
    balanceMap.put(user, balanceMap.getOrDefault(user, 0) + money);
  }

  public void withdrawMoney(User user, int money) {
    balanceMap.put(user, Math.max(0, balanceMap.getOrDefault(user, 0) - money));
  }
}
