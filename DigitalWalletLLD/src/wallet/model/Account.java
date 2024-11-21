package wallet.model;

import java.math.BigDecimal;

public class Account {

  private final String accountId;
  private final String ifsc;

  public BigDecimal balance;

  public Account(String accountId, String ifsc) {
    this.accountId = accountId;
    this.ifsc = ifsc;
    this.balance = new BigDecimal("0");
  }

  public String getAccountId() {
    return this.accountId;
  }

  public String getIfsc() {
    return this.ifsc;
  }

  public BigDecimal getBalance() {
    return this.balance;
  }

  public boolean depositMoney(BigDecimal amount) {
    balance = balance.add(amount);
    return true;
  }

  public boolean withdrawMoney(BigDecimal amount) {
    if (this.balance.compareTo(amount) >= 0) {
      balance = balance.subtract(amount);
      return true;
    }
    return false;
  }
}
