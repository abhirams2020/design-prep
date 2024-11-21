package ticketbooking.model;

import ticketbooking.enums.BookingStatus;
import ticketbooking.enums.SeatType;

public class Seat {
  public String id;
  public BookingStatus bookingStatus;
  public SeatType seatType;

  public Seat(String id, SeatType seatType) {
    this.id = id;
    bookingStatus = BookingStatus.AVAILABLE;
    this.seatType = seatType;
  }
}
