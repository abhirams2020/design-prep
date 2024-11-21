package linkedin.model;

public class Education {
  public College college;
  public Integer startYear;
  public Integer endYear;
  public String description;

  public Education(College college, Integer startYear, Integer endYear, String description) {
    this.college = college;
    this.startYear = startYear;
    this.endYear = endYear;
    this.description = description;
  }
}
