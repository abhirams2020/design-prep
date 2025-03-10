package splitwiselld.model.expense;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import splitwiselld.enums.SplitType;
import splitwiselld.model.User;

public class ExactExpense implements Expense{
  SplitType splitType;
  User paidUser;
  Map<User, Double> owedUserMap;

  public ExactExpense(User user, Map<User, Double> owedMap, double totalAmount) {
    splitType = SplitType.EXACT;
    paidUser = user;
    owedUserMap = new HashMap<>();

    int sum = 0;

    for(User owedUser:owedMap.keySet()) {
      sum += owedMap.get(owedUser);
    }

    // validate sum = totalAmount

    for(User owedUser:owedMap.keySet()) {
      if(owedUser.equals(paidUser)) continue;
      owedUserMap.put(owedUser, owedMap.get(owedUser));
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
