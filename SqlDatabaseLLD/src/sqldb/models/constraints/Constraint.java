package sqldb.models.constraints;

public interface Constraint {
  boolean validate(Object object);
}
