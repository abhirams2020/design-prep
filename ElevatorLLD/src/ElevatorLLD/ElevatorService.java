package ElevatorLLD;

import java.util.List;

public class ElevatorService {
  static int floorCount = 10;
  static int elevatorCount = 2;
  List<Floor> floorList;
  List<ElevatorManager> elevatorList;

  private static ElevatorService instance;

  // singleton pattern
  private ElevatorService(int floorCount, int elevatorCount) {
    ElevatorService.floorCount = floorCount;
    ElevatorService.elevatorCount = elevatorCount;
  }

  public static ElevatorService getInstance() {
    if(instance == null) {
      synchronized(ElevatorService.class) {
        if(instance==null) {
          instance = new ElevatorService(floorCount, elevatorCount);
        }
      }
    }
    return instance;
  }

  public void setFloorCount(int floorCount) {
    ElevatorService.floorCount = floorCount;
  }

  public void setElevatorCount(int elevatorCount) {
    ElevatorService.elevatorCount = elevatorCount;
  }
}
