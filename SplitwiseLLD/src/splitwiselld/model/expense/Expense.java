package splitwiselld.model.expense;

import java.util.List;
import java.util.Map;
import splitwiselld.enums.SplitType;
import splitwiselld.model.User;

public interface Expense {
  SplitType getSplitType();
  User getPaidUser();
  Map<User, Double> getOwedUserAmount();
}
