package splitwiselld.model.expense;

import java.util.HashMap;
import java.util.Map;
import splitwiselld.enums.SplitType;
import splitwiselld.model.User;

public class PercentExpense implements Expense{
  SplitType splitType;
  User paidUser;
  Map<User, Double> owedUserMap;

  public PercentExpense(User user, Map<User, Double> owedMap, double totalAmount) {
    splitType = SplitType.PERCENT;
    paidUser = user;
    owedUserMap = new HashMap<>();

    int sum = 0;

    for(User owedUser:owedMap.keySet()) {
      sum += owedMap.get(owedUser);
    }

    // validate sum = 100

    for(User owedUser:owedMap.keySet()) {
      if(owedUser.equals(paidUser)) continue;
      owedUserMap.put(owedUser, totalAmount * owedMap.get(owedUser)/100);
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
