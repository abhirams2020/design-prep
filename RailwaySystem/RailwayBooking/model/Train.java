class Seat {
  int id;
  int number;
  SeatType seatType;
  BookingStatus bookingStatus;

  public boolean isBooked() {
    return bookingStatus==BookingStatus.RESERVED;
  }

  public boolean book() {
    if(isBooked()) {
      return false;
    }
    bookingStatus = BookingStatus.BOOKED;
    return true;
  }
}

class Bogey {
  int id;
  int number;
  BogeyType bogeyType;
  List<Seat> seatList;

  public List<Seat> getFreeSeats() {
    // return list of unbooked seats
  }
}

public class Train {
  int id;
  String name;
  int trainCode;
  List<Bogey> bogeyList;

}

public class Journey {
  int id;
  Station startStation;

}

public class BookingManager() {


}