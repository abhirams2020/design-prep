package wallet.model.enums;

public enum CurrencyType {
  INR(0.5),
  USD(1);

  public double value;

  CurrencyType(double value) {
    this.value = value;
  }

  public double getValue() {
    return this.value;
  }
}
