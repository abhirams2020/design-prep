package sqldb.models.constraints;

public class StringConstraint implements Constraint{
  private static final int MAX_LENGTH = 20;

  @Override
  public boolean validate(Object object) {
    return (object instanceof String) && (((String)object).length() <= MAX_LENGTH);
  }
}
