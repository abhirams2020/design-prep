package LibrarySystem.model.user;

public class NormalUser implements User{
  String id;
  String name;

  public NormalUser(String id, String name) {
    this.id = id;
    this.name = name;
  }
}
