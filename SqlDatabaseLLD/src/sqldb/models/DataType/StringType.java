package sqldb.models.DataType;

import sqldb.models.constraints.Constraint;

public class StringType implements DataType{
  Constraint constraint;

  public StringType(Constraint constraint) {
    this.constraint = constraint;
  }

  public StringType() {
  }

  @Override
  public boolean validate(Object object) {
    return object instanceof String && (constraint==null || constraint.validate(object));
  }
}
