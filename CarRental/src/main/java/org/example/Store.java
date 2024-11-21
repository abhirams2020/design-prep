package org.example;

import java.util.ArrayList;
import java.util.List;

public class Store {
  int storeId;
  List<Vehicle> vehicles = new ArrayList<>();

  public int getStoreId() {
    return this.storeId;
  }

  public List<Vehicle> getVehicles(VehicleType vehicleType) {
    return vehicles.stream().filter(x->x.vehicleType==vehicleType).toList();
  }

  public void setStoreId(int id) {
    this.storeId = id;
  }

  public void setVehicles(List<Vehicle> vehicles) {
    this.vehicles = vehicles;
  }
}
