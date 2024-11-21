package VendingMachineLLD.states.impl;

import VendingMachineLLD.Product;
import VendingMachineLLD.VendingMachine;
import java.util.List;
import VendingMachineLLD.money.Coin;
import VendingMachineLLD.states.VendingMachineState;

public class DispenseProductState implements VendingMachineState {
  VendingMachine vendingMachine;
  public DispenseProductState(VendingMachine vendingMachine) {
    this.vendingMachine = vendingMachine;
  }

  @Override
  public void selectProduct(Product product) {
    System.out.println("product already selected");
  }

  @Override
  public void insertCoin(List<Coin> coins) {
    System.out.println("coin already inserted");
  }

  @Override
  public void returnChange() {
    System.out.println("change already returned");
  }

  @Override
  public void dispenseProduct() {
    System.out.println("dispensed product : " + vendingMachine.selectedProduct.getType().name()
        + " of price : $" + vendingMachine.selectedProduct.getPrice());
    vendingMachine.removeProduct(vendingMachine.selectedProduct);
    vendingMachine.setState(new IdleState(vendingMachine));
  }
}
