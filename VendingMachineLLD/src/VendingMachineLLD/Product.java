package VendingMachineLLD;

public class Product {
  private final ProductType type;
  private final double price;
  private boolean soldOut;

  public Product(ProductType type, double price) {
    this.type = type;
    this.price = price;
    soldOut = false;
  }

  public ProductType getType() {
    return type;
  }
  public double getPrice() {
    return price;
  }

  public void setSoldOut() {
    this.soldOut = true;
  }
}
