package VendingMachineLLD.states.impl;

import VendingMachineLLD.Product;
import VendingMachineLLD.VendingMachine;
import java.util.List;
import VendingMachineLLD.money.Coin;
import VendingMachineLLD.states.VendingMachineState;

public class SelectedState implements VendingMachineState {
  VendingMachine vendingMachine;

  public SelectedState(VendingMachine vendingMachine) {
    this.vendingMachine = vendingMachine;
  }

  @Override
  public void selectProduct(Product product) {
    System.out.println("product already selected");
  }

  @Override
  public void insertCoin(List<Coin> coins) {
    double totalAmount = 0;
    for(Coin coin:coins) {
      totalAmount += coin.getValue();
    }
    if(totalAmount < vendingMachine.selectedProduct.getPrice()) {
      System.out.println("insufficient balance for selected product : $" + totalAmount);
      vendingMachine.refundMoney();
      vendingMachine.setState(new IdleState(vendingMachine));
      return;
    }
    System.out.println("amount received from user : $" + totalAmount);
    vendingMachine.setTotalAmount(vendingMachine.getTotalAmount() + totalAmount);
    vendingMachine.setState(new ReturnChangeState(vendingMachine));
  }

  @Override
  public void returnChange() {
    System.out.println("no VendingMachineLLD.money received from user");
  }

  @Override
  public void dispenseProduct() {
    System.out.println("no VendingMachineLLD.money received from user");
  }
}
