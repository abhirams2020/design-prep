package PropertyHuntApp.repository;

import PropertyHuntApp.model.user.User;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserRepo {

  public Map<String, User> userMap = new HashMap<>();
  public Set<User> loginSet = new HashSet<>();

  public void register(User user) {
    userMap.putIfAbsent(user.getUsername(), user);
  }

  public User login(String username) {
    User user = userMap.get(username);
    if (user == null) {
      throw new RuntimeException("user not registered");
    }
    loginSet.add(user);
    return userMap.get(username);
  }

  public void logout(User user) {
    loginSet.remove(user);
  }

  public boolean isLoggedIn(User user) {
    return loginSet.contains(user);
  }
}
