package linkedin.model;

import java.util.ArrayList;
import java.util.List;

public class User {
  public String name;
  public String email;
  public String password;
  public Profile profile;

  public User(String email, String password, String name) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.profile = new Profile();
  }
}
