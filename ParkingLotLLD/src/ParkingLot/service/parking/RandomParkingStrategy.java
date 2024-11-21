package ParkingLot.service.parking;

import ParkingLot.Level;
import ParkingLot.ParkingSlot;
import ParkingLot.model.vehicle.Vehicle;

public class RandomParkingStrategy implements ParkingStrategy{

  @Override
  public boolean park(Level level, Vehicle vehicle) {
    for(ParkingSlot parkingSlot:level.getSlotList()) {
      if(parkingSlot.park(vehicle)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean unpark(Level level, Vehicle vehicle) {
    for(ParkingSlot parkingSlot:level.getSlotList()) {
      if(parkingSlot.unpark(vehicle)) {
        return true;
      }
    }
    return false;
  }
}
