package service;

import enums.Size;
import java.util.List;
import model.Locker;

public interface LockerMatchingStrategy {
  Locker match(List<Locker> lockerList, Size size);
}
