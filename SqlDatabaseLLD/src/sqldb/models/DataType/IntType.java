package sqldb.models.DataType;

import sqldb.models.constraints.Constraint;

public class IntType implements DataType{
  Constraint constraint;

  public IntType(Constraint constraint) {
    this.constraint = constraint;
  }

  public IntType() {
  }

  @Override
  public boolean validate(Object object) {
    return object instanceof Integer && (constraint==null || constraint.validate(object));
  }
}
