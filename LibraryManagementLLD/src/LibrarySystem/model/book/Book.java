package LibrarySystem.model.book;

import LibrarySystem.enums.BookStatus;

public class Book implements ReadItem{
  public String id;
  public String title;
  public String author;
  public String isbn;
  public String publicationYear;
  public BookStatus bookStatus;
  public int pricePerDay;

  public Book(String id, String title, String author, String isbn, String publicationYear, int pricePerDay) {
    this.id = id;
    this.title = title;
    this.author = author;
    this.publicationYear = publicationYear;
    this.isbn = isbn;
    this.pricePerDay = pricePerDay;
    this.bookStatus = BookStatus.AVAILABLE;
  }

  @Override
  public boolean borrowItem() {
    if(bookStatus == BookStatus.AVAILABLE) {
      bookStatus = BookStatus.NOT_AVAILABLE;
      return true;
    }
    return false;
  }

  @Override
  public void returnItem() {
    bookStatus = BookStatus.AVAILABLE;
  }

  @Override
  public BookStatus getStatus() {
    return bookStatus;
  }

  @Override
  public void setStatus(BookStatus bookStatus) {
    this.bookStatus = bookStatus;
  }

  @Override
  public int getPricePerDay() {
    return pricePerDay;
  }

  @Override
  public String getTitle() {
    return this.title;
  }
}
