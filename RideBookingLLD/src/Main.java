import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

enum UserType {
  DEFAULT,
  PREMIUM
}

enum VehicleType {
  BIKE,
  CAR
}

abstract class User {
  String id;
  String name;
  UserType userType;

  public User(String name, String phoneNumber) {
    this.name = name;
    this.id = phoneNumber;
    this.userType = UserType.DEFAULT;
  }

  public UserType getUserType() {
    return userType;
  }
}

class DefaultUser extends User {
  public DefaultUser(String name, String phoneNumber) {
    super(name, phoneNumber);
  }
}

class PremiumUser extends User {
  UserType userType;
  public PremiumUser(String name, String phoneNumber) {
    super(name, phoneNumber);
    this.userType = UserType.PREMIUM;
  }

  @Override
  public UserType getUserType() {
    return userType;
  }
}

abstract class Driver {
  String id, name, phoneNumber;
  VehicleType vehicleType;

  public Driver(String name, String phoneNumber) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.vehicleType = VehicleType.BIKE;
  }

  public VehicleType getVehicleType() {
    return vehicleType;
  }
}

class BikeDriver extends Driver {
  public BikeDriver(String name, String phoneNumber) {
    super(name, phoneNumber);
  }
}

class CarDriver extends Driver {
  public CarDriver(String name, String phoneNumber) {
    super(name, phoneNumber);
  }

  @Override
  public VehicleType getVehicleType() {
    return VehicleType.CAR;
  }
}

class Location {
  String location;
  public Location(String location) {
    this.location = location;
  }
  public String getLocation() {
    return location;
  }
}

enum BookingStatus {
  ONGOING, REACHED, PAID
}

class Booking {
  String id;
  String userId;
  String driverId;
  Location start, end;
  Double price;
  LocalDate date;
  BookingStatus bookingStatus;

  public Booking(String userId, String driverId, Location start, Location end, Double price) {
    this.id = UUID.randomUUID().toString();
    this.userId = userId;
    this.driverId = driverId;
    this.start = start;
    this.end = end;
    this.price = price;
    this.date = LocalDate.now();
    this.bookingStatus = BookingStatus.ONGOING;
  }

  public void setStatus(BookingStatus bookingStatus) {
    this.bookingStatus = bookingStatus;
  }
}

interface PaymentStrategy {
  public void makePayment(Double price);
}

class UpiStrategy implements PaymentStrategy {
  private final String senderId;
  private final String receiverId;

  public UpiStrategy(String senderId, String receiverId) {
    this.senderId = senderId;
    this.receiverId = receiverId;
  }

  @Override
  public void makePayment(Double price) {
    System.out.println("payment done");
  }
}

class UserRepo {
  public static Map<String, User> userMap = new ConcurrentHashMap<>();
}

class DriverRepo {
  public static Map<String, Driver> driverMap = new ConcurrentHashMap<>();
  public static Map<String, Location> driverLocationMap = new ConcurrentHashMap<>();
}

interface MatchingStrategy {
  public List<Driver> getMatches(Location start, Location end, Double price, VehicleType vehicleType);
}

class NearestMatchingStrategy implements MatchingStrategy {

  @Override
  public List<Driver> getMatches(Location start, Location end, Double price, VehicleType vehicleType) {
    List<Driver> driverList = new ArrayList<>();
    for(String driver:DriverRepo.driverLocationMap.keySet()) {
      if(DriverRepo.driverLocationMap.get(driver).location.equals(start.location)) {
        driverList.add(DriverRepo.driverMap.get(driver));
      }
    }
    return driverList;
  }
}

class BookingService {
  public static final BookingService instance = new BookingService();
  private MatchingStrategy matchingStrategy = new NearestMatchingStrategy();

  public String userCreateAccount(String username, String phoneNumber, UserType userType) {
    User user;
    if(userType.equals(UserType.DEFAULT)) {
      user = new DefaultUser(username, phoneNumber);
      UserRepo.userMap.putIfAbsent(user.id, user);
    } else {
      user = new PremiumUser(username, phoneNumber);
      UserRepo.userMap.putIfAbsent(user.id, user);
    }
    return user.id;
  }

  public User userLogin(String phoneNumber) {
    return UserRepo.userMap.getOrDefault(phoneNumber, null);
  }

  public void driverCreateAccount(String username, String phoneNumber, VehicleType vehicleType) {
    if(vehicleType.equals(VehicleType.BIKE)) {
      DriverRepo.driverMap.putIfAbsent(phoneNumber, new BikeDriver(username, phoneNumber));
    } else {
      DriverRepo.driverMap.putIfAbsent(phoneNumber, new CarDriver(username, phoneNumber));
    }
  }

  public Driver driverLogin(String phoneNumber) {
    return DriverRepo.driverMap.getOrDefault(phoneNumber, null);
  }

  public void setDriverLocation(String phoneNumber, Location location) {
    DriverRepo.driverLocationMap.put(phoneNumber, location);
  }

  public Booking findRide(String userId, Location start, Location end, Double price, VehicleType vehicleType) {
    List<Driver> driverList = matchingStrategy.getMatches(start,end,price,vehicleType);
    Booking booking
        = new Booking(userId, driverList.get(ThreadLocalRandom.current().nextInt()%driverList.size())
          .id, start, end, price);
    return booking;
  }
}


public class Main {
  public static void main(String[] args) {
  }
}