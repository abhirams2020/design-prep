package VendingMachineLLD;

import java.util.concurrent.ConcurrentHashMap;

public class Inventory {
  ConcurrentHashMap<Product, Integer> mp = new ConcurrentHashMap<>();

  public void addProduct(Product product, int quantity) {
    mp.put(product, mp.getOrDefault(product,0) + quantity);
  }

  public void removeProduct(Product product, int quantity) {
    if(mp.containsKey(product)) {
      int newVal = mp.get(product) - quantity;
      if(newVal > 0) {
        mp.put(product, newVal);
      }
    }
  }

  public Product getProductOfType(ProductType type) {
    for(Product product:mp.keySet()) {
      if(product.getType() == type) {
        return product;
      }
    }
    return null;
  }

  public boolean checkAvailable(Product product) {
    return mp.containsKey(product) && mp.get(product)>0;
  }

  public void displayProducts() {
    System.out.println("-----------------");
    System.out.println("Product\tPrice\tQuantity");
    for(Product product : mp.keySet()) {
      System.out.println(product.getType().name() + "\t" + product.getPrice() + "\t" + mp.get(product));
    }
    System.out.println("-----------------");
  }
}
