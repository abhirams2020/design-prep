package PropertyHuntApp.model.size;

import PropertyHuntApp.enums.AreaUnit;

public class MetricSize implements Size {

  Integer size;
  AreaUnit areaUnit;


  public MetricSize(Integer size) {
    this.size = size;
    this.areaUnit = AreaUnit.METRIC;
  }

  public AreaUnit getAreaUnit() {
    return areaUnit;
  }

  public Integer getSize() {
    return size;
  }
}
