package AtmManagement.model.user;

import AtmManagement.model.account.AccountNumber;
import AtmManagement.model.account.Password;

public class NormalUser implements User{
  String id, name;
  public AccountNumber accountNumber;
  public Password password;

  public NormalUser(String id, String name) {
    this.id = id;
    this.name = name;
    this.accountNumber = null;
    this.password = null;
  }

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public AccountNumber getAccount() {
    return this.accountNumber;
  }

  @Override
  public Password getPassword() {
    return this.password;
  }

  @Override
  public void setAccountNumber(AccountNumber accountNumber) {
    this.accountNumber = accountNumber;
  }

  @Override
  public void setPassword(Password password) {
    this.password = password;
  }
}
