package splitwiselld.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Group {
  String id, name;
  List<User> members;
  BalanceSheet balanceSheet;

  public Group(String name) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    this.members = new ArrayList<>();
    this.balanceSheet = new BalanceSheet();
  }

  public Group(String name, List<User> members) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    this.members = new ArrayList<>(members);
    this.balanceSheet = new BalanceSheet();
  }

  public void addUser(User user) {
    members.add(user);
  }

  public void removeUser(User user) {
    members.remove(user);
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<User> getMembers() {
    return members;
  }

  public BalanceSheet getBalanceSheet() {
    return balanceSheet;
  }
}
