package AtmManagement.model.user;

import AtmManagement.model.account.AccountNumber;
import AtmManagement.model.account.Password;

public interface User {

  String getId();

  String getName();

  AccountNumber getAccount();

  Password getPassword();

  void setAccountNumber(AccountNumber accountNumber);

  void setPassword(Password password);
}
