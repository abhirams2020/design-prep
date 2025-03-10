package snakeLadder.service;

import java.util.HashMap;
import java.util.Map;
import snakeLadder.model.Player;

public class UserService {
  private static volatile UserService instance;
  private Map<String, Player> map;

  public static UserService getInstance() {
    if(instance==null) {
      synchronized (UserService.class) {
        if(instance==null) {
          instance = new UserService();
        }
      }
    }
    return instance;
  }

  private UserService() {
    map = new HashMap<>();
  }

  public String createUser(String name) {
    Player player = new Player(name);
    map.putIfAbsent(player.getId(), player);
    return player.getId();
  }

  public Player getUserById(String id) {
    return map.get(id);
  }
}
