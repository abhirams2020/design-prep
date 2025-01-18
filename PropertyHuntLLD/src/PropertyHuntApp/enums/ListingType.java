package PropertyHuntApp.enums;

public enum ListingType {
  SELL("SELL"),
  RENT("RENT");

  String value;

  ListingType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
