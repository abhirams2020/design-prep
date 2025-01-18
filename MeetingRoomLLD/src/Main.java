import java.awt.print.Book;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

class User {
  String id;
  String name;

  public User(String id, String name) {
    this.id = id;
    this.name = name;
  }
}

class Location {
  int level;
  int x;
  int y;

  public Location(int level, int x, int y) {
    this.level = level;
    this.x = x;
    this.y = y;
  }
}

enum RoomStatus {
  AVAILABLE,
  RESERVED,
  UNDER_MAINTENANCE
}

class MeetingRoom {
  String id;
  String name;
  Location location;
  RoomStatus roomStatus;
  int capacity;

  public MeetingRoom(String id, String name, Location location, RoomStatus roomStatus, int capacity) {
    this.id = id;
    this.name = name;
    this.location = location;
    this.roomStatus = roomStatus;
    this.capacity = capacity;
  }
}

class Booking {
  String id;
  String userId;
  String meetingRoomId;
  List<String> participantList;
  long startTime;
  long endTime;

  public Booking(String id, String userId, String meetingRoomId, List<String> participantList, long startTime, long endTime) {
    this.id = id;
    this.userId = userId;
    this.meetingRoomId = meetingRoomId;
    this.participantList = participantList;
    this.startTime = startTime;
    this.endTime = endTime;
  }
}

class MeetingRoomService {
  public static final MeetingRoomService instance = new MeetingRoomService();

  private MeetingRoomService() {}

  public static Map<String, MeetingRoom> meetingRoomMap = new ConcurrentHashMap<>();

  public void addMeetingRoom(String id, String name, Location location, int capacity) {
    MeetingRoom meetingRoom = new MeetingRoom(id,name,location,RoomStatus.AVAILABLE,capacity);
    meetingRoomMap.put(id, meetingRoom);
  }

  public void removeMeetingRoom(String id) {
    meetingRoomMap.remove(id);
  }
 }

 class Message {
  String message;

  public Message(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
 }

 class UserService {
  public static final UserService instance = new UserService();

  public static Map<String, User> userMap = new ConcurrentHashMap<>();

  public void createUser(String id, String name) {
    User user = new User(id,name);
    userMap.putIfAbsent(id, user);
  }

  public void removeUser(String id) {
    userMap.remove(id);
  }

  public void getNotification(String userId, Message message) {
    System.out.println(userId + " : " + message.getMessage());
  }
 }

 interface BookingStrategy {
  String makeBooking(String userId, List<String> participants, long start, long end,
      List<String> roomList, Location location);
 }

 class NeareastBookingStrategy implements BookingStrategy {

  private static long getDistance(Location a, Location b) {
    return (long)(a.x-b.x)*(a.x-b.x) + (long)(a.y - b.y) *(a.y-b.y);
  }
  public String makeBooking(String userId, List<String> participants, long start, long end,
      List<String> roomList, Location location) {
    PriorityQueue<String> pq = new PriorityQueue<>((m,n)->{
      Location a = MeetingRoomService.meetingRoomMap.get(m).location;
      Location b = MeetingRoomService.meetingRoomMap.get(n).location;

      int levelDiff = Math.abs(a.level - location.level) - Math.abs(b.level - location.level);

      if (levelDiff != 0) {
        return levelDiff; // Compare by level difference
      } else {
        return Double.compare(getDistance(a, location), getDistance(b, location)); // Compare by distance
      }
    });

    for(String room:roomList) {
      pq.add(room);
    }

    if(pq.isEmpty()){
      return null;
    }

    String selectedMeetingRoom = pq.peek();

    return selectedMeetingRoom;
  }
 }

 class BookingService {
  public static final BookingService instance = new BookingService(new NeareastBookingStrategy());

  private BookingStrategy bookingStrategy;

  private BookingService(BookingStrategy bookingStrategy) {
    this.bookingStrategy = bookingStrategy;
  }

  public static Map<String, List<Booking>> meetingBookingMap = new ConcurrentHashMap<>();
  public static Map<String, Booking> bookingMap = new ConcurrentHashMap<>();

  private final MeetingRoomService meetingRoomService = MeetingRoomService.instance;

  public boolean checkAvailability(String roomId, long start, long end, int capacity) {
    if(!MeetingRoomService.meetingRoomMap.containsKey(roomId)) {
      return false;
    }

    MeetingRoom meetingRoom = MeetingRoomService.meetingRoomMap.get(roomId);
    if(meetingRoom.capacity < capacity || !meetingRoom.roomStatus.equals(RoomStatus.AVAILABLE)) {
      return false;
    }

    meetingBookingMap.putIfAbsent(roomId, new ArrayList<>());
    for(Booking booking:meetingBookingMap.get(roomId)) {
      if(!(booking.startTime > end || booking.endTime < start)) {
        return false;
      }
    }

    return true;
  }

  public List<String> getAvailable(long start, long end, int capacity) {
    List<String> roomList = new ArrayList<>();
    for(String roomId:MeetingRoomService.meetingRoomMap.keySet()) {
      if(checkAvailability(roomId, start, end, capacity)) {
        roomList.add(roomId);
      }
    }
    return roomList;
  }

  public String bookMeeting(String userId, List<String> participants, long start, long end, Location location) {
    List<String> meetingRooms = getAvailable(start,end, 1 + participants.size());

    if(meetingRooms.isEmpty()) {
      System.out.println("no meeting rooms available");
      return "";
    }

    String roomId = bookingStrategy.makeBooking(userId, participants, start, end, meetingRooms, location);

    if(roomId == null) {
      System.out.println("no meeting rooms available");
      return "";
    }
    Booking booking = new Booking(UUID.randomUUID().toString(), userId, roomId, participants, start, end);
//    MeetingRoomService.meetingRoomMap.get(booking.meetingRoomId).roomStatus = RoomStatus.RESERVED;

    bookingMap.putIfAbsent(booking.id, booking);
    meetingBookingMap.get(roomId).add(booking);

    NotificationService.instance.notifyParticipants(booking.id);
    return booking.id;
  }
 }

 class NotificationService {
  public static final NotificationService instance = new NotificationService();

  public void notifyParticipants(String bookingId) {
    Booking booking = BookingService.bookingMap.get(bookingId);
    for(String user:booking.participantList) {
      UserService.instance.getNotification(user, new Message(booking.meetingRoomId +
          "booked from " + booking.startTime + " to " + booking.endTime));
    }
  }
 }

public class Main {

  public static void main(String[] args) {
    BookingService bookingService = BookingService.instance;
    UserService userService = UserService.instance;
    MeetingRoomService meetingRoomService = MeetingRoomService.instance;

    userService.createUser("user1", "abhiram");
    userService.createUser("user2", "hari");
    userService.createUser("user3", "kamal");
    userService.createUser("user4", "kumar");

    meetingRoomService.addMeetingRoom("room1", "Paris", new Location(0,1,1), 10);
    meetingRoomService.addMeetingRoom("room2", "London", new Location(0,2,2), 2);

    bookingService.bookMeeting("user1", List.of("user2","user3","user4"), 1, 2, new Location(0, 0,0));
    bookingService.bookMeeting("user1", List.of("user2","user3","user4"), 1, 2, new Location(0, 0,0));

  }
}