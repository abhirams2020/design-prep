package ParkingLot.model.vehicle;

import ParkingLot.enums.VehicleType;

public class Bike implements Vehicle{
  int vehicleSize;
  String registrationId;
  VehicleType vehicleType;

  public Bike(String reg) {
    vehicleSize = 2;
    registrationId = reg;
    vehicleType = VehicleType.BIKE;
  }

  @Override
  public int getSize() {
    return vehicleSize;
  }

  @Override
  public String getRegistrationId() {
    return registrationId;
  }

  @Override
  public VehicleType getType() {
    return this.vehicleType;
  }
}
