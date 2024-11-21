package ParkingLot.model.vehicle;

import ParkingLot.enums.VehicleType;

public interface Vehicle {
  public int getSize();

  public String getRegistrationId();

  public VehicleType getType();
}
