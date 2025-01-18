package PropertyHuntApp.model.user;

import PropertyHuntApp.enums.UserType;
import java.util.UUID;

public class DefaultUser implements User {

  String id;
  String username;
  UserType userType;

  public DefaultUser(String username) {
    this.username = username;
    this.id = UUID.randomUUID().toString();
    this.userType = UserType.DEFAULT;
  }

  public String getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public UserType getUserType() {
    return userType;
  }

  public void setUserType(UserType userType) {
    this.userType = userType;
  }
}
