package wallet.model;

import java.math.BigDecimal;

public class User {

  public String id;
  public Profile profile;
  public String emailId;
  public String password;
  public BigDecimal balance;
  public Account account;

  public User(String id, String emailId, String password, Account account) {
    this.id = id;
    this.emailId = emailId;
    this.password = password;
    this.profile = new Profile();
    this.balance = BigDecimal.valueOf(0);
    this.account = account;
  }
}
