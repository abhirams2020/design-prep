package AtmManagement;

import AtmManagement.model.account.AccountNumber;
import AtmManagement.model.account.Password;
import AtmManagement.model.user.NormalUser;
import AtmManagement.model.user.User;
import java.util.concurrent.ConcurrentHashMap;

public class AuthService {

  public static AuthService instance = new AuthService();

  private ConcurrentHashMap<String, User> userMap;
  private ConcurrentHashMap<String, String> passwordMap;

  private AuthService() {
    userMap = new ConcurrentHashMap<>();
    passwordMap = new ConcurrentHashMap<>();
  }

  public void createAccount(String name, String account, String password) {
    if(userMap.containsKey(account)) {
      return;
    }
    User user = new NormalUser("123", name);
    user.setAccountNumber(new AccountNumber(account));
    user.setPassword(new Password(password));

    userMap.put(account, user);
    passwordMap.put(account, password);
  }

  public User login(String account, String password) {
    if(userMap.containsKey(account) && passwordMap.get(account)==password) {
      return userMap.get(account);
    }
    return null;
  }
}
