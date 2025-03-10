package splitwiselld.model;

import java.util.UUID;

public class User {
  String id, name;
  BalanceSheet balanceSheet;

  public User(String name) {
    this.name = name;
    this.id = UUID.randomUUID().toString();
    this.balanceSheet = new BalanceSheet();
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public BalanceSheet getBalanceSheet() {
    return balanceSheet;
  }
}
