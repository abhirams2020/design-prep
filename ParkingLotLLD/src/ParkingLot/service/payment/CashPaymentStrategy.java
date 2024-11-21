package ParkingLot.service.payment;

public class CashPaymentStrategy implements PaymentStrategy{

  @Override
  public boolean payBill(int amount) {
    System.out.println("bill paid for amount " + amount + " using cash");
    return true;
  }
}
