package ParkingLot.model.ticket;

public class Ticket {
  int amount;
  int ticketId;
  private static int ticketCounter = 0;

  public Ticket(int amount) {
    this.ticketId = ticketCounter;
    this.amount = amount;
    ticketCounter++;
  }

  public void print() {
    System.out.println("Ticket " + ticketId + " with amount : " + amount);
  }
}
