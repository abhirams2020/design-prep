package VendingMachineLLD.states.impl;

import VendingMachineLLD.Product;
import VendingMachineLLD.VendingMachine;
import java.util.List;
import VendingMachineLLD.money.Coin;
import VendingMachineLLD.states.VendingMachineState;

public class ReturnChangeState implements VendingMachineState {
  VendingMachine vendingMachine;

  public ReturnChangeState(VendingMachine vendingMachine) {
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
    double returnAmount = vendingMachine.getTotalAmount() - vendingMachine.selectedProduct.getPrice();
    if(returnAmount < 0) {
      System.out.println("insufficient for selected product. returned full VendingMachineLLD.money");
      vendingMachine.refundMoney();
      vendingMachine.setState(new IdleState(vendingMachine));
    } else {
      System.out.println("returned change : $" +  returnAmount);
      vendingMachine.resetTotalAmount();
      vendingMachine.setState(new DispenseProductState(vendingMachine));
    }
  }

  @Override
  public void dispenseProduct() {
    System.out.println("wait for change return");
  }
}
