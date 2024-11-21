package ParkingLot;

import ParkingLot.enums.VehicleType;
import ParkingLot.model.vehicle.Vehicle;
import ParkingLot.service.ParkingLotService;
import ParkingLot.service.payment.CardPaymentStrategy;
import ParkingLot.service.payment.CashPaymentStrategy;
import ParkingLot.service.payment.PaymentStrategy;
import ParkingLot.service.vehiclefactory.VehicleFactory;

public class ParkingLotDemo {

  public static void main(String[] args) {
    int levelSize = 2;
    ParkingLotService parkingLotService = new ParkingLotService(levelSize);
    initialize(parkingLotService);

    VehicleFactory vehicleFactory = VehicleFactory.getInstance();

    Vehicle vehicle1 = vehicleFactory.getVehicle(VehicleType.BIKE,"1234");
    Vehicle vehicle2 = vehicleFactory.getVehicle(VehicleType.CAR,"1024");
    parkingLotService.enter(vehicle1);
    parkingLotService.enter(vehicle2);

    boolean isParked1 = parkingLotService.park(vehicle1);
    boolean isParked2 = parkingLotService.park(vehicle2);

    parkingLotService.displayStatus();

    if(!isParked1) {
      System.out.println("no space for vehicle1");
    }
    if(!isParked2) {
      System.out.println("no space for vehicle2");
    }

    parkingLotService.unpark(vehicle1);
    parkingLotService.unpark(vehicle2);

    PaymentStrategy paymentStrategy1 = new CashPaymentStrategy();
    PaymentStrategy paymentStrategy2 = new CardPaymentStrategy("12345678");

    parkingLotService.payBill(vehicle1, paymentStrategy1);
    parkingLotService.payBill(vehicle2, paymentStrategy2);

    parkingLotService.exit(vehicle1);
    parkingLotService.exit(vehicle2);

    parkingLotService.displayStatus();
  }

  public static void initialize(ParkingLotService parkingLot) {
    for(int i=0;i<10;i++) {
      if(i%2==0) {
        parkingLot.addSlot(0, 2);
      } else {
        parkingLot.addSlot(0, 4);
      }
    }

    for(int i=0;i<10;i++) {
      if(i<5) {
        parkingLot.addSlot(1,2);
      } else {
        parkingLot.addSlot(1,4);
      }
    }
  }
}
