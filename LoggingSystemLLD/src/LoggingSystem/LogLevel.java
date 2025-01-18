package LoggingSystem;

public enum LogLevel {
  DEBUG(0),
  INFO(1),
  WARNING(2),
  ERROR(3),
  FATAL(4);

  private final int value;

  private LogLevel(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
