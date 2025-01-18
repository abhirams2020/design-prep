package PropertyHuntApp.service.factory;

import PropertyHuntApp.enums.UserType;
import PropertyHuntApp.model.user.DefaultUser;
import PropertyHuntApp.model.user.User;

public class UserFactory {
  public static User getUser(String name, UserType userType) {
    if(userType==UserType.DEFAULT) {
      return new DefaultUser(name);
    } else {
      throw new IllegalArgumentException("invalid user type input");
    }
  }
}
