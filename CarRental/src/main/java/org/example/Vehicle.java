package org.example;

public abstract class Vehicle {
  int vehicleId;
  VehicleType vehicleType;

  public void setVehicleID(int id) {
    this.vehicleId = id;
  }

  public void setVehicleType(VehicleType vehicleType) {
    this.vehicleType = vehicleType;
  }

  public int getVehicleId() {
    return this.vehicleId;
  }

  public VehicleType vehicleType() {
    return this.vehicleType;
  }
}
