package LibrarySystem.model.book;

import LibrarySystem.enums.BookStatus;

public interface ReadItem {
  public boolean borrowItem();
  public void returnItem();
  public BookStatus getStatus();
  public void setStatus(BookStatus bookStatus);
  public int getPricePerDay();
  public String getTitle();
}
