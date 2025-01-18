package PropertyHuntApp.model;

public class Location {
  String name;
  String geohash;

  public Location(String name) {
    this.name = name;
    this.geohash = "000000";
  }

  public Location(String name, String geohash) {
    this.name = name;
    this.geohash = geohash;
  }
}
