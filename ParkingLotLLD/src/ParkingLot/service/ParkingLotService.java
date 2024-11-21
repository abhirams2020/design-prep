package ParkingLot.service;

import ParkingLot.ParkingLot;
import ParkingLot.model.vehicle.Vehicle;
import ParkingLot.service.payment.PaymentStrategy;

public class ParkingLotService {

  ParkingLot parkingLot;
  TicketService ticketService;

  public ParkingLotService(int levels) {
    parkingLot = new ParkingLot(levels);
    ticketService = new TicketService();
  }

  public void enter(Vehicle vehicle) {
    if(!parkingLot.checkSlotAvailable(vehicle)) {
      return;
    }
    ticketService.addVehicleTicket(vehicle);
    System.out.println("Vehicle " + vehicle.getRegistrationId() + " entered");
  }

  public void exit(Vehicle vehicle) {
    if(ticketService.checkPending(vehicle)) {
      System.out.println("Vehicle "+vehicle.getRegistrationId()+" has pending bills");
    } else {
      System.out.println("Vehicle " + vehicle.getRegistrationId() + " exited");
    }
  }

  public boolean park(Vehicle vehicle) {
    return parkingLot.park(vehicle);
  }

  public boolean unpark(Vehicle vehicle) {
    return parkingLot.unpark(vehicle);
  }

  public void displayStatus() {
    parkingLot.displayStatus();
  }

  public void addSlot(int level, int size) {
    parkingLot.addSlot(level,size);
  }

  public void payBill(Vehicle vehicle, PaymentStrategy paymentStrategy) {
    ticketService.payBill(vehicle, paymentStrategy);
    ticketService.removeVehicleTicket(vehicle);
  }
}
