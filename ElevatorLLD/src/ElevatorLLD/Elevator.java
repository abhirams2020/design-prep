package ElevatorLLD;

public class Elevator {
  int id;
  int currentFloor;
  int capacity;
  int peopleCount;
  Direction direction;
  ElevatorManager elevatorManager;

  public Elevator(int id, int capacity) {
    this.id = id;
    this.capacity = capacity;
    elevatorManager = ElevatorManager.getInstance();
    currentFloor = 0;
    direction = Direction.UP;
  }

  public void addPeople(int people) {
    peopleCount += people;
  }

  public void removePeople()

  public void changeDirection() {
    if(direction == Direction.UP) {
      direction = Direction.DOWN;
    } else {
      direction = Direction.UP;
    }
  }

  public void moveUp() {
    currentFloor++;
  }

  public void moveDown() {
    currentFloor--;
  }
}
