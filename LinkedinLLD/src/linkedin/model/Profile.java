package linkedin.model;

import java.util.ArrayList;
import java.util.List;

public class Profile {
  public ProfilePic profilePic;
  public String summary;
  public List<Skill> skillList;
  public List<Experience> experienceList;
  public List<Education> educationList;

  public Profile() {
    this.skillList = new ArrayList<>();
    this.experienceList = new ArrayList<>();
    this.educationList = new ArrayList<>();
  }
}
