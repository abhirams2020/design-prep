package sqldb.models.constraints;

public class IntConstraint implements Constraint{

  private final int MIN_LIMIT;
  private final int MAX_LIMIT;

  public IntConstraint() {
    this.MIN_LIMIT = -1024;
    this.MAX_LIMIT = 1024;
  }

  public IntConstraint(int MIN_LIMIT, int MAX_LIMIT) {
    this.MIN_LIMIT = MIN_LIMIT;
    this.MAX_LIMIT = MAX_LIMIT;
  }

  @Override
  public boolean validate(Object object) {
    if(!(object instanceof Integer)) {
      return false;
    }
    int val = (int) object;
    return val>=MIN_LIMIT && val<=MAX_LIMIT;
  }
}
