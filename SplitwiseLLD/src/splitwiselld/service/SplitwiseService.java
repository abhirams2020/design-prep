package splitwiselld.service;

import java.util.HashMap;
import java.util.Map;
import splitwiselld.model.Group;
import splitwiselld.model.User;
import splitwiselld.model.expense.Expense;

public class SplitwiseService {

  UserService userService;
  GroupService groupService;

  private SplitwiseService() {
    userService = UserService.getInstance();
    groupService = GroupService.getInstance();
  }

  public static final SplitwiseService INSTANCE = new SplitwiseService();

  public static SplitwiseService getInstance() {
    return INSTANCE;
  }

  public void createAccount(User user) {
    userService.createAccount(user);
  }

  public void createGroup(Group group) {
    groupService.createGroup(group);
  }

  public void addUserExpense(User user, Expense expense) {
    userService.addExpense(expense);
  }

  public void addGroupExpense(User user, Group group, Expense expense) {
    groupService.addExpense(user, group, expense);
  }

  public void settleUserExpense(User user, User owed) {
    userService.settleUserExpense(user, owed);
  }

  public void settleGroupExpense(User user, Group group) {
    groupService.settleGroupExpense(user, group);
  }

//  public String viewUserBalance(User user) {
//    return user.getBalanceSheet().getBalanceMap().getOrDefault(user, new HashMap<>()).toString();
//  }
//
//  public String viewGroupBalanceUser(User user, Group group) {
//    return group.getBalanceSheet().getBalanceMap().getOrDefault(user, new HashMap<>()).toString();
//  }
//
//  public String viewGroupBalance(Group group) {
//    return group.getBalanceSheet().getBalanceMap().toString();
//  }

  public String viewUserBalance(User user) {
    StringBuilder sb = new StringBuilder();
    sb.append("Balance for ").append(user.getName()).append(":\n");

    Map<User, Double> balanceMap = user.getBalanceSheet().getBalanceMap().getOrDefault(user, new HashMap<>());

    if (balanceMap.isEmpty()) {
      sb.append("No outstanding balances.\n");
    } else {
      for (Map.Entry<User, Double> entry : balanceMap.entrySet()) {
        if (entry.getValue() < 0) {
          sb.append("Owes ").append(entry.getKey().getName()).append(": ₹").append(-entry.getValue()).append("\n");
        } else {
          sb.append("Receives from ").append(entry.getKey().getName()).append(": ₹").append(entry.getValue()).append("\n");
        }
      }
    }
    return sb.toString();
  }

  public String viewGroupBalanceUser(User user, Group group) {
    StringBuilder sb = new StringBuilder();
    sb.append("Balance for ").append(user.getName()).append(" in Group: ").append(group.getName()).append("\n");

    Map<User, Double> balanceMap = group.getBalanceSheet().getBalanceMap().getOrDefault(user, new HashMap<>());

    if (balanceMap.isEmpty()) {
      sb.append("No outstanding balances in this group.\n");
    } else {
      for (Map.Entry<User, Double> entry : balanceMap.entrySet()) {
        if (entry.getValue() < 0) {
          sb.append("Owes ").append(entry.getKey().getName()).append(": ₹").append(-entry.getValue()).append("\n");
        } else {
          sb.append("Receives from ").append(entry.getKey().getName()).append(": ₹").append(entry.getValue()).append("\n");
        }
      }
    }
    return sb.toString();
  }

  public String viewGroupBalance(Group group) {
    StringBuilder sb = new StringBuilder();
    sb.append("Group Balance for ").append(group.getName()).append(":\n");

    Map<User, Map<User, Double>> balanceSheet = group.getBalanceSheet().getBalanceMap();

    if (balanceSheet.isEmpty()) {
      sb.append("No outstanding balances in the group.\n");
    } else {
      for (Map.Entry<User, Map<User, Double>> entry : balanceSheet.entrySet()) {
        sb.append(entry.getKey().getName()).append("'s balances:\n");
        for (Map.Entry<User, Double> innerEntry : entry.getValue().entrySet()) {
          if (innerEntry.getValue() < 0) {
            sb.append("  Owes ").append(innerEntry.getKey().getName()).append(": ₹").append(-innerEntry.getValue()).append("\n");
          } else {
            sb.append("  Receives from ").append(innerEntry.getKey().getName()).append(": ₹").append(innerEntry.getValue()).append("\n");
          }
        }
      }
    }
    return sb.toString();
  }


}
