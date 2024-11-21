package ParkingLot.service;

import ParkingLot.enums.VehicleType;
import ParkingLot.model.vehicle.Vehicle;
import java.time.Duration;
import java.time.LocalDateTime;

public class PricingService {
  public static int generateBill(Vehicle vehicle, LocalDateTime startTime, LocalDateTime endTime) {
    System.out.println("start time : " + startTime + ", endtime : " + endTime);
    int totalHours = (int)(Duration.between(startTime, endTime.plusHours(1)).toHours()); // round to nearest ceil
    if(vehicle.getType().equals(VehicleType.CAR)) {
      return 20*totalHours;
    } else if (vehicle.getType().equals(VehicleType.BIKE)) {
      return 10*totalHours;
    } else {
      return 0;
    }
  }
}
