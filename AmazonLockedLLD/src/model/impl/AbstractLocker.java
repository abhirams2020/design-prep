package model.impl;

import enums.LockerStatus;
import enums.Size;
import java.nio.file.LinkOption;
import java.util.List;
import model.Locker;

public abstract class AbstractLocker implements Locker {
  List<Size> eligibleSizes;
  Size size;
  LockerStatus status;

  public AbstractLocker(Size size, List<Size> eligibleSizes) {
    this.size = size;
    this.eligibleSizes = eligibleSizes;
    this.status = LockerStatus.AVAILABLE;
  }

  public Size getSize() {
    return this.size;
  }

  public boolean isAvailable() {
    return LockerStatus.AVAILABLE.equals(status);
  }

  public void markAvailable() {
    this.status = LockerStatus.AVAILABLE;
  }

  public void markOccupied() {
    this.status = LockerStatus.OCCUPIED;
  }

  public void open() {
    System.out.println("Locker door opened");
  }

  public void close() {
    System.out.println("Locker door closed");
  }
}
