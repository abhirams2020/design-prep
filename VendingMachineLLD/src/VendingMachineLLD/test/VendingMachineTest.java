package VendingMachineLLD.test;

import VendingMachineLLD.Product;
import VendingMachineLLD.ProductType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VendingMachineTest {
  VendingMachineLLD.VendingMachine vendingMachine = new VendingMachineLLD.VendingMachine();

  @Test
  public void testCheckAvailable() {
    vendingMachine.addProduct(new Product(ProductType.COKE, 1), 3);
    vendingMachine.addProduct(new Product(ProductType.PEPSI, 0.5), 2);
    vendingMachine.addProduct(new Product(ProductType.JUICE, 0.25), 2);
    vendingMachine.addProduct(new Product(ProductType.SODA, 0.1), 3);
    vendingMachine.displayInventory();

    assertFalse(vendingMachine.checkAvailable(new Product(ProductType.COKE, 2)));
  }
}
