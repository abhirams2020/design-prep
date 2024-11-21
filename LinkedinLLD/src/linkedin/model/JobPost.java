package linkedin.model;

import java.util.List;

public class JobPost {
  public String id;
  public Company company;
  public String title;
  public String location;
  public List<Skill> skillList;

  public JobPost(String id, Company company, String title, String location, List<Skill> skillList) {
    this.id = id;
    this.company = company;
    this.title = title;
    this.location = location;
    this.skillList = skillList;
  }
}
