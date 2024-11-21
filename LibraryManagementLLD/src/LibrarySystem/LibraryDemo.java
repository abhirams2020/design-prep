package LibrarySystem;

import LibrarySystem.model.book.Book;
import LibrarySystem.model.book.ReadItem;
import LibrarySystem.model.user.NormalUser;
import LibrarySystem.model.user.User;

public class LibraryDemo {

  public static void main(String[] args) {
    LibraryService libraryService = LibraryService.getInstance();

    ReadItem book1 = new Book("123", "oldbook", "abhiram", "123456", "2010", 10);
    ReadItem book2 = new Book("234", "oldbook", "abhiram", "123456", "2010", 10);
    ReadItem book3 = new Book("345", "newbook", "kumar", "232332", "2022", 5);

    libraryService.addBook(book1);
    libraryService.addBook(book2);
    libraryService.addBook(book3);

    User user1 = new NormalUser("123", "karl");
    User user2 = new NormalUser("987", "marx");

    libraryService.addUser(user1);
    libraryService.addUser(user2);

    libraryService.displayLibrary();

    ReadItem book = libraryService.checkAvailability("oldbook");

    if(book != null) {
      libraryService.borrowBook(user1, book1, "12-10-2022");
    }

    libraryService.displayLibrary();

    int totalCharge = libraryService.returnBook(user1, book1, "12-11-2022");

    libraryService.payCharge(user1, book1, totalCharge);

    libraryService.displayLibrary();

    libraryService.getUserStatus(user1);

  }
}
