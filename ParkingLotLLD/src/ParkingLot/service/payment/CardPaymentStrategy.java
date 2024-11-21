package ParkingLot.service.payment;

public class CardPaymentStrategy implements PaymentStrategy{

  String bankaccount;

  public CardPaymentStrategy(String bankaccount) {
    this.bankaccount = bankaccount;
  }

  @Override
  public boolean payBill(int amount) {
    System.out.println("bill paid for amount " + amount + " using card : " + bankaccount);
    return true;
  }
}
