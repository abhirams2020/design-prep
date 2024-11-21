package ParkingLot;

import ParkingLot.model.vehicle.Vehicle;
import ParkingLot.service.parking.ParkingStrategy;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
  List<Level> levelList;

  public ParkingLot(int levelSize) {
    levelList = new ArrayList<>();
    for(int i=0;i<levelSize;i++) {
      levelList.add(new Level());
    }
  }

  public void addSlot(int level, int slotSize) {
    levelList.get(level).addSlot(slotSize);
  }

  public void setParkingStrategy(int level, ParkingStrategy parkingStrategy) {
    levelList.get(level).setParkingStrategy(parkingStrategy);
  }

  public boolean park(Vehicle vehicle) {
    for(Level level:levelList) {
      if(level.park(vehicle)) {
        return true;
      }
    }
    return false;
  }

  public boolean unpark(Vehicle vehicle) {
    for(Level level:levelList) {
      if(level.unpark(vehicle)) {
        return true;
      }
    }
    return false;
  }

  public boolean checkSlotAvailable(Vehicle vehicle) {
    for(Level level:levelList) {
      if(level.checkSlotAvailable(vehicle)) {
        return true;
      }
    }
    return false;
  }

  public void displayStatus() {
    for(Level level:levelList) {
      level.displayStatus();
    }
  }
}
