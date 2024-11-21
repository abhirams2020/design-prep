package ParkingLot.model.vehicle;

import ParkingLot.enums.VehicleType;

public class Car implements Vehicle{
  int vehicleSize;
  String registrationId;
  VehicleType vehicleType;

  public Car(String reg) {
    vehicleSize = 4;
    this.registrationId = reg;
    vehicleType = VehicleType.CAR;
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
