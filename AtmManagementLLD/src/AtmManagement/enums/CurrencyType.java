package AtmManagement.enums;

public enum CurrencyType {
  COIN1(1),
  COIN5(5),
  NOTE10(10),
  NOTE20(20),
  NOTE50(50),
  NOTE100(100),
  NOTE500(500);

  private int value;

  private CurrencyType(int value) {
    this.value = value;
  }

  public int getValue() {
    return this.value;
  }
}
