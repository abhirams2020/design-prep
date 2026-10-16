package model;

import enums.Size;

public interface Locker {
  Size getSize();
  boolean isAvailable();
  void markAvailable();
  void markOccupied();
  void open();
  void close();
}
