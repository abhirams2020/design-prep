import enums.Size;
import service.LockerManager;

public class Main {

  public static void main(String[] args) {
    System.out.println("Hello world!");

    LockerManager manager = LockerManager.getInstance();

    manager.addLocker(Size.SMALL);
    manager.addLocker(Size.LARGE);

    manager.printLockers();

    String code = manager.depositPackage(Size.MEDIUM);

    manager.printLockers();

    try {
      manager.pickupPackage(code);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    manager.printLockers();
  }
}