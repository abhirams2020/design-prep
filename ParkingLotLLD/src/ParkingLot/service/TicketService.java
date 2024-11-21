package ParkingLot.service;

import ParkingLot.model.vehicle.Vehicle;
import ParkingLot.service.payment.PaymentStrategy;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

public class TicketService {
  ConcurrentHashMap<String, LocalDateTime> ticketMap;

  public TicketService() {
    ticketMap = new ConcurrentHashMap<>();
  }

  public void addVehicleTicket(Vehicle vehicle) {
    ticketMap.put(vehicle.getRegistrationId(), LocalDateTime.now());
  }

  public void removeVehicleTicket(Vehicle vehicle) {
    ticketMap.remove(vehicle.getRegistrationId());
  }

  public boolean checkPending(Vehicle vehicle) {
    return ticketMap.containsKey(vehicle.getRegistrationId());
  }

  public int generateBill(Vehicle vehicle) {
    return PricingService.generateBill(vehicle, ticketMap.get(vehicle.getRegistrationId()),
        LocalDateTime.now());
  }

  public boolean payBill(Vehicle vehicle, PaymentStrategy paymentStrategy) {
    return paymentStrategy.payBill(generateBill(vehicle));
  }
}
