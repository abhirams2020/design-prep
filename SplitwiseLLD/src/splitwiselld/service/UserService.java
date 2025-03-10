package splitwiselld.service;
import java.util.*;
import splitwiselld.model.Group;
import splitwiselld.model.User;
import splitwiselld.model.expense.ExactExpense;
import splitwiselld.model.expense.Expense;

public class UserService {
  Set<User> userSet;
  Map<User, Set<Group>> userGroupMap;

  private static final UserService INSTANCE = new UserService();

  private UserService() {
    this.userSet = new HashSet<>();
    this.userGroupMap = new HashMap<>();
  }

  public static UserService getInstance() {
    return INSTANCE;
  }

  public void createAccount(User user) {
    userSet.add(user);
  }

  public void addUserGroup(User user, Group group) {
    userGroupMap.computeIfAbsent(user, k -> new HashSet<>()).add(group);
  }

  public void removeUserGroup(User user, Group group) {
    userGroupMap.computeIfAbsent(user, k -> new HashSet<>()).remove(group);
  }

  public List<Group> getUserGroups(User user) {
    return List.copyOf(userGroupMap.getOrDefault(user, new HashSet<>()));
  }

  public Map<User, Double> getBalanceSheet(User user) {
    return user.getBalanceSheet().getBalanceMap().getOrDefault(user, new HashMap<>());
  }

  public void addExpense(Expense expense) {
    Map<User, Double> balanceMap = expense.getOwedUserAmount();
    User paidUser = expense.getPaidUser();
    for(User user:balanceMap.keySet()) {
      if(user.equals(paidUser)) continue;
      paidUser.getBalanceSheet().payForUser(paidUser, user, balanceMap.get(user));
      user.getBalanceSheet().getFromUser(user, paidUser, balanceMap.get(user));
    }
  }

  public void settleUserExpense(User user, User owed) {
    Double amt = owed.getBalanceSheet().getBalanceMap().getOrDefault(owed, new HashMap<>()).getOrDefault(user, (double)0);
    if(amt > 0) {
      addExpense(new ExactExpense(user, Map.of(owed, amt), amt));
    }
  }
}
