package ticketbooking;

public class TicketBookingDemo {

  public static void main(String[] args) {
    initialize();

    BookingService bookingService = BookingService.instance;

    City city = new City("Bangalore");
    List<Theatre> theatreList = bookingService.getTheatreOfCity(city);
    Theatre theatre = theatreList.get(0);

    List<Movie> movieList = bookingService.getMoviesOfTheatre(theatre);

    Movie movie = movieList.get(0);

    List<Timing> timingList = bookingService.getTimingMovieTheatre()
  }

  public static void initialize() {}
}
