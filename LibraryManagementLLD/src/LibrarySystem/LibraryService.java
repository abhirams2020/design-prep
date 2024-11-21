package LibrarySystem;

import LibrarySystem.enums.BookStatus;
import LibrarySystem.model.book.ReadItem;
import LibrarySystem.model.user.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class LibraryService {
  public static LibraryService instance = new LibraryService();

  CopyOnWriteArrayList<ReadItem> readItemList;
  ConcurrentHashMap<User, HashMap<ReadItem, String>> userMap;

  private LibraryService() {
    readItemList = new CopyOnWriteArrayList<>();
    userMap = new ConcurrentHashMap<>();
  }

  public static LibraryService getInstance() {
    return instance;
  }


  public void addBook(ReadItem book) {
    readItemList.add(book);
  }

  public void addUser(User user1) {
    userMap.put(user1, new HashMap<>());
  }

  public ReadItem checkAvailability(String bookName) {
    for(ReadItem readItem:readItemList) {
      if(readItem.getStatus() == BookStatus.AVAILABLE) {
        return readItem;
      }
    }
    return null;
  }

  public void borrowBook(User user, ReadItem book, String borrowDate) {
    book.setStatus(BookStatus.NOT_AVAILABLE);
    userMap.put(user, new HashMap<>());
    userMap.get(user).put(book, borrowDate);
  }

  public int returnBook(User user, ReadItem book, String returnDate) {
    book.setStatus(BookStatus.AVAILABLE);
    String borrowDate = userMap.get(user).get(book);
    int pricePerDay = book.getPricePerDay();
    return calculateTotalCharge(borrowDate, returnDate, pricePerDay);
  }

  private int calculateTotalCharge(String borrowDate, String returnDate, int pricePerDay) {
    return 100;
  }

  public void payCharge(User user, ReadItem book, int totalCharge) {
    userMap.get(user).remove(book);
  }


  public void getUserStatus(User user) {
    
  }

  public void displayLibrary() {
    System.out.println("Displaying library");
    for(ReadItem readItem:readItemList) {
      System.out.println(readItem.getTitle() + " : " + readItem.getStatus().name());
    }
  }
}
