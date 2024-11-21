package ElevatorLLD;

public class Floor {
  int floorNo;

  public void addRequest(Direction direction) {
    ElevatorManager elevatorManager = ElevatorManager.getInstance();
    elevatorManager.addRequest(direction);
  }
}
