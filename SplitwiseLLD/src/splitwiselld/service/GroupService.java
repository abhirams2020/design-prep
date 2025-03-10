package splitwiselld.service;

import java.util.*;
import splitwiselld.model.Group;
import splitwiselld.model.User;
import splitwiselld.model.expense.ExactExpense;
import splitwiselld.model.expense.Expense;

public class GroupService {
  Set<Group> groupSet;

  private static final GroupService INSTANCE = new GroupService();

  private GroupService() {
    this.groupSet = new HashSet<>();
  }

  public static GroupService getInstance() {
    return INSTANCE;
  }

  public void createGroup(Group group) {
    groupSet.add(group);
  }

  public Map<User, Map<User, Double>> getBalanceSheet(Group group) {
    return group.getBalanceSheet().getBalanceMap();
  }

  public void addExpense(User paidUser, Group group, Expense expense) {
    Map<User, Double> balanceMap = expense.getOwedUserAmount();
    for(User user:balanceMap.keySet()) {
      if(user.equals(paidUser)) continue;
      group.getBalanceSheet().payForUser(paidUser, user, balanceMap.get(user));
      group.getBalanceSheet().getFromUser(user, paidUser, balanceMap.get(user));
    }
    UserService.getInstance().addExpense(expense);
  }

  public void settleGroupExpense(User user, Group group) {
    Map<User, Double> balanceMap = group.getBalanceSheet().getBalanceMap().getOrDefault(user, new HashMap<>());
    Map<User, Double> expenseMap = new HashMap<>();
    double total = 0;
    for(User owed:balanceMap.keySet()) {
      double amt = -balanceMap.get(owed);
      if(amt <= 0) continue;
      total += amt;
      expenseMap.put(owed, amt);
    }
    Expense expense = new ExactExpense(user, expenseMap, total);
    addExpense(user, group, expense);
  }
}
