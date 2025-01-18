package PropertyHuntApp.model.user;

import PropertyHuntApp.enums.UserType;

public interface User {

  String getId();

  String getUsername();

  UserType getUserType();

  void setUserType(UserType userType);
}
