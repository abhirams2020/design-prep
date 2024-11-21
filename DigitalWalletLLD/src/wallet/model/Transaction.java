package wallet.model;

import java.math.BigDecimal;

public class Transaction {

  public String id;
  public User sender;
  public User receiver;
  public String timestamp;
  public BigDecimal amount;

  public Transaction(String id, User sender, User receiver, String timestamp, BigDecimal amount) {
    this.id = id;
    this.sender = sender;
    this.receiver = receiver;
    this.timestamp = timestamp;
    this.amount = amount;
  }
}
