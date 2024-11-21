package ParkingLot;

import ParkingLot.model.vehicle.Vehicle;
import ParkingLot.service.parking.ParkingStrategy;
import ParkingLot.service.parking.RandomParkingStrategy;
import java.util.ArrayList;
import java.util.List;

public class Level {
  ParkingStrategy parkingStrategy;
  List<ParkingSlot> slotList;

  public Level() {
    this.slotList = new ArrayList<>();
    this.parkingStrategy = new RandomParkingStrategy();
  }

  public void setParkingStrategy(ParkingStrategy parkingStrategy) {
    this.parkingStrategy = parkingStrategy;
  }

  public List<ParkingSlot> getSlotList() {
    return slotList;
  }

  public void addSlot(int slotSize) {
    slotList.add(new ParkingSlot(slotSize));
  }

  public boolean park(Vehicle vehicle) {
    return parkingStrategy.park(this, vehicle);
  }

  public boolean unpark(Vehicle vehicle) {
    return parkingStrategy.unpark(this, vehicle);
  }

  public boolean checkSlotAvailable(Vehicle vehicle) {
    for(ParkingSlot slot:slotList) {
      if(slot.checkSlotAvailable(vehicle)) {
        return true;
      }
    }
    return false;
  }

  public void displayStatus() {
    String levelStatus = "";
    for(ParkingSlot slot:slotList) {
      levelStatus += slot.getParkingStatus() + " ,";
    }
    System.out.println(levelStatus);
  }
}
