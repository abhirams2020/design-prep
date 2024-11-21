package wallet.model;

public class Profile {

  public String name;
  public String location;
  public Integer age;

  public Profile() {
    this.name = "";
    this.location = "";
    this.age = 0;
  }

  public Profile(String name, String location, Integer age) {
    this.name = name;
    this.location = location;
    this.age = age;
  }
}
