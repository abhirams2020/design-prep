package PropertyHuntApp.enums;

import java.awt.geom.Area;

public enum AreaUnit {
  METRIC("sqm"),
  FEET("sqft");

  String value;

  private AreaUnit(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
