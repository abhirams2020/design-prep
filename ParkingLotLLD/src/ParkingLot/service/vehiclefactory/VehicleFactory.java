package ParkingLot.service.vehiclefactory;

import ParkingLot.enums.VehicleType;
import ParkingLot.model.vehicle.Vehicle;
import ParkingLot.model.vehicle.Car;
import ParkingLot.model.vehicle.Bike;

public class VehicleFactory {

  private static VehicleFactory instance;

  private VehicleFactory() {}

  public static VehicleFactory getInstance() {
    if(instance==null) {
      synchronized (VehicleFactory.class) {
        if(instance==null) {
          instance = new VehicleFactory();
        }
      }
    }
    return instance;
  }

  public Vehicle getVehicle(VehicleType vehicleType, String reg) {
    if(vehicleType.equals(VehicleType.CAR)) {
      return new Car(reg);
    } else if (vehicleType.equals(VehicleType.BIKE)) {
      return new Bike(reg);
    } else {
      return null;
    }
  }
}
