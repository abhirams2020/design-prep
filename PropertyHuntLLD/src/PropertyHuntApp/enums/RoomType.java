package PropertyHuntApp.enums;

public enum RoomType {
  BHK1("1BHK"),
  BHK2("2BHK"),
  BHK3("3BHK"),
  RK1("1RK");

  String value;

  RoomType(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
