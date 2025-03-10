package instagramlld.dao;

import instagramlld.model.User;
import instagramlld.service.UserService;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserRepoImpl {
  private Map<String, String> usernameIdMap;
  private Map<String, User> userMap;
  private Set<String> loginSet;
  private Map<String, Set<String>> followerMap;
  private Map<String, Set<String>> followingMap;

  private UserRepoImpl() {
    this.usernameIdMap = new HashMap<>();
    this.userMap = new HashMap<>();
    this.loginSet = new HashSet<>();
    this.followerMap = new HashMap<>();
    this.followingMap = new HashMap<>();
  }

  public static volatile UserRepoImpl instance = null;

  public static UserRepoImpl getInstance() {
    if(instance==null) {
      synchronized (UserService.class) {
        if(instance==null) {
          instance = new UserRepoImpl();
        }
      }
    }
    return instance;
  }

  public void register(String username, String password, String name) {
    if(isAccountExist(username)) {
      return;
    }
    User user = new User(username, password, name);
    usernameIdMap.put(user.getUsername(), user.getId());
    userMap.put(user.getId(), user);
  }

  public String login(String username, String password) {
    if(!isAccountExist(username)){
      throw new IllegalArgumentException("user not registered");
    }
    String id = usernameIdMap.get(username);
    if(!isLoggedIn(id)) {
      loginSet.add(id);
    }
    return id;
  }

  public void logout(String userId) {
    loginSet.remove(userId);
  }

  public void follow(String userId1, String userId2) {
    followingMap.putIfAbsent(userId1, new HashSet<>());
    followerMap.putIfAbsent(userId2, new HashSet<>());
    followingMap.get(userId1).add(userId2);
    followerMap.get(userId2).add(userId1);
  }

  public List<String> getFollowers(String userId) {
    return List.copyOf(followerMap.getOrDefault(userId, new HashSet<>()));
  }

  public List<String> getFollowing(String userId) {
    return List.copyOf(followingMap.getOrDefault(userId, new HashSet<>()));
  }

  public boolean isLoggedIn(String userId) {
    return loginSet.contains(userId);
  }

  public boolean isAccountExist(String username) {
    return usernameIdMap.containsKey(username);
  }

  public String getIdFromUsername(String username) {
    return userMap.get(username).getId();
  }

  public User getUserFromId(String id) {
    return userMap.get(id);
  }
}
