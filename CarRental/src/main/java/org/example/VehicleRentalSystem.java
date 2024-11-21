package org.example;

import java.util.ArrayList;
import java.util.List;

public class VehicleRentalSystem {
  List<User> users = new ArrayList<>();
//  List<Vehicle> vehicles = addVehicles();
  List<Store> stores = new ArrayList<>();

  public VehicleRentalSystem(List<Store> stores, List<User> users) {
    this.users = users;
    this.stores = stores;
  }

  public Store getStore(Location location) {
    return stores.getFirst();
  }
}
