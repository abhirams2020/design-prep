package VendingMachineLLD;

import java.util.ArrayList;
import java.util.List;
import VendingMachineLLD.money.Coin;
import VendingMachineLLD.states.VendingMachineState;
import VendingMachineLLD.states.impl.IdleState;

public class VendingMachine {
  Inventory inventory;
  double totalBalance;
  public Product selectedProduct;
  List<Coin> coins;

  VendingMachineState vendingMachineState;

  public VendingMachine() {
    vendingMachineState = new IdleState(this);
    inventory = new Inventory();
    totalBalance = 0;
    coins = new ArrayList<>();
  }

  public void setState(VendingMachineState vendingMachineState) {
    this.vendingMachineState = vendingMachineState;
  }

  public void displayInventory() {
    inventory.displayProducts();
  }

  public void addProduct(Product product, int quantity) {
    inventory.addProduct(product,quantity);
  }

  public void removeProduct(Product product) {
    inventory.removeProduct(product,1);
  }

  public double getTotalAmount() {
    return totalBalance;
  }

  public void setTotalAmount(double amount) {
    this.totalBalance = amount;
  }

  public void resetTotalAmount() {
    setTotalAmount(0.0);
  }

  public boolean checkAvailable(Product product) {
    return inventory.checkAvailable(product);
  }

  public void refundMoney() {
    System.out.println("returned : $" + totalBalance);
    resetTotalAmount();
  }

  public void addCoinInMachine(Coin coin) {
    coins.add(coin);
  }

  // state methods
  public void selectProduct(ProductType type) {
    Product product = inventory.getProductOfType(type);
    if(product == null) {
      System.out.println("product not available");
      return;
    }
    this.vendingMachineState.selectProduct(product);
  }

  public void insertCoin() {
    this.vendingMachineState.insertCoin(coins);
  }

  public void returnChange() {
    this.vendingMachineState.returnChange();
  }

  public void dispenseProduct() {
    this.vendingMachineState.dispenseProduct();
  }
}
