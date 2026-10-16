package model.impl;

import enums.Size;
import model.Locker;

public final class LockerFactory {
  public static Locker createLocker(Size size) {
    return switch (size) {
      case SMALL -> new SmallLocker();
      case LARGE -> new LargeLocker();
      case MEDIUM -> new MediumLocker();
    };
  }
}
