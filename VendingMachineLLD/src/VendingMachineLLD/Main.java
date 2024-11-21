package VendingMachineLLD;

import VendingMachineLLD.money.Coin;

public class Main {

  public static void main(String args[]){

    VendingMachine vendingMachine = new VendingMachine();
    try {
      System.out.println("filling up the inventory");

      fillUpInventory(vendingMachine);
      displayInventory(vendingMachine);

      System.out.println("choose a product");

      vendingMachine.selectProduct(ProductType.JUICE);

      vendingMachine.addCoinInMachine(Coin.QUARTER);
      vendingMachine.addCoinInMachine(Coin.QUARTER);
      vendingMachine.addCoinInMachine(Coin.QUARTER);
      vendingMachine.addCoinInMachine(Coin.QUARTER);
      vendingMachine.addCoinInMachine(Coin.DIME);

      vendingMachine.insertCoin();
      vendingMachine.returnChange();
      vendingMachine.dispenseProduct();

      displayInventory(vendingMachine);

    }
    catch (Exception e){
      displayInventory(vendingMachine);
    }
  }

  private static void fillUpInventory(VendingMachine vendingMachine){
      vendingMachine.addProduct(new Product(ProductType.COKE, 1), 3);
      vendingMachine.addProduct(new Product(ProductType.PEPSI, 0.5), 2);
      vendingMachine.addProduct(new Product(ProductType.JUICE, 0.25), 2);
      vendingMachine.addProduct(new Product(ProductType.SODA, 0.1), 3);
  }

  private static void displayInventory(VendingMachine vendingMachine){
    vendingMachine.displayInventory();
  }
}
