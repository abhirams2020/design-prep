package SearchEngine.services;

import SearchEngine.models.User;
import java.util.HashSet;
import java.util.Set;

public class UserService {
  Set<User> users = new HashSet<>();

  public User createUser(String name) {
    User user = new User(name);
    users.add(user);
    return user;
  }
}
