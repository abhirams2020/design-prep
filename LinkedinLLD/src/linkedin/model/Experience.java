package linkedin.model;

public class Experience {
  public Company company;
  public Integer startYear;
  public Integer endYear;
  public String description;

  public Experience(Company company, Integer startYear, Integer endYear, String description) {
    this.company = company;
    this.startYear = startYear;
    this.endYear = endYear;
    this.description = description;
  }
}
