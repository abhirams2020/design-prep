package splitwiselld.model.expense;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import splitwiselld.enums.SplitType;
import splitwiselld.model.User;

public class EqualExpense implements Expense{
  SplitType splitType;
  User paidUser;
  Map<User, Double> owedUserMap;

  public EqualExpense(User user, List<User> owedUsers, double totalAmount) {
    splitType = SplitType.EQUAL;
    paidUser = user;
    owedUserMap = new HashMap<>();
    Double amountPerUser = totalAmount/owedUsers.size();
    for(User owedUser:owedUsers) {
      if(!owedUser.equals(paidUser)) {
        owedUserMap.put(owedUser, amountPerUser);
      }
    }
  }

  @Override
  public SplitType getSplitType() {
    return splitType;
  }

  @Override
  public User getPaidUser() {
    return paidUser;
  }

  @Override
  public Map<User, Double> getOwedUserAmount() {
    return owedUserMap;
  }
}
