package VendingMachineLLD.states;

import VendingMachineLLD.Product;
import java.util.List;
import VendingMachineLLD.money.Coin;

public interface VendingMachineState {
  public void selectProduct(Product product);
  public void insertCoin(List<Coin> coins);
  public void returnChange();
  public void dispenseProduct();
}
