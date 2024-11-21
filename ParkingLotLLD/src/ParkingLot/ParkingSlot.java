package ParkingLot;

import ParkingLot.enums.ParkingStatus;
import ParkingLot.model.vehicle.Vehicle;

public class ParkingSlot {
  Integer slotSize;
  Vehicle parkedVehicle;
  ParkingStatus parkingStatus;

  public ParkingSlot(int slotSize) {
    this.slotSize = slotSize;
    this.parkedVehicle = null;
    this.parkingStatus = ParkingStatus.AVAILABLE;
  }

  public boolean park(Vehicle vehicle) {
    if(parkedVehicle!=null || vehicle.getSize() > slotSize) {
      return false;
    }
    parkedVehicle = vehicle;
    parkingStatus = ParkingStatus.PARKED;
    return true;
  }

  public boolean unpark(Vehicle vehicle) {
    if(parkedVehicle==null || !parkedVehicle.equals(vehicle)) {
      return false;
    }
    parkedVehicle = null;
    parkingStatus = ParkingStatus.AVAILABLE;
    return true;
  }

  public boolean checkSlotAvailable(Vehicle vehicle) {
    return vehicle.getSize()<=slotSize && parkedVehicle==null;
  }

  public String getParkingStatus() {
    return slotSize.toString() + "(" + parkingStatus.name() + ")";
  }
}
