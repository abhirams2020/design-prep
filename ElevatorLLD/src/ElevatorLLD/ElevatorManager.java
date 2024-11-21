package ElevatorLLD;

import java.util.List;

// each elevator has elevator manager
public class ElevatorManager {
  Elevator elevator;

  public ElevatorManager(int id, int capacity) {
    elevator = new Elevator(id, capacity);
  }

  public int getCurrentFloor() {
    return elevator.currentFloor;
  }

  public int getCurrentCapacity() {
    return elevator.capacity;
  }

  public synchronized int addPeople() {
    Direction temp = elevator.direction;
    elevator.direction = Direction.IDLE;
    if()
  }
}
