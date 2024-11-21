package VendingMachineLLD.states.impl;

import VendingMachineLLD.Product;
import VendingMachineLLD.VendingMachine;
import java.util.List;
import VendingMachineLLD.money.Coin;
import VendingMachineLLD.states.VendingMachineState;

public class IdleState implements VendingMachineState {

  VendingMachine vendingMachine;

  public IdleState(VendingMachine vendingMachine) {
    this.vendingMachine = vendingMachine;
  }

  @Override
  public void selectProduct(Product product) {
    if(vendingMachine.checkAvailable(product) == false) {
      System.out.println("product " + product.getType().name() + " not available");
      vendingMachine.refundMoney();
      this.vendingMachine.setState(new IdleState(vendingMachine));
      return;
    }
    System.out.println("selected product " + product.getType().name());
    this.vendingMachine.selectedProduct = product;
    this.vendingMachine.setState(new SelectedState(vendingMachine));
  }

  @Override
  public void insertCoin(List<Coin> coins) {
    System.out.println("select product first");
  }

  @Override
  public void returnChange() {
    System.out.println("select product first");
  }

  @Override
  public void dispenseProduct() {
    System.out.println("select product first");
  }
}
