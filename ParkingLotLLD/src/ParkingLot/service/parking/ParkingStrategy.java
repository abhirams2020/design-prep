package ParkingLot.service.parking;

import ParkingLot.Level;
import ParkingLot.model.vehicle.Vehicle;

public interface ParkingStrategy {

  boolean park(Level level, Vehicle vehicle);

  boolean unpark(Level level, Vehicle vehicle);
}
