package instagramlld.model;

import java.util.UUID;
import lombok.Getter;

@Getter
public class User {
  String id, username, password, name;

  public User(String username, String password, String name) {
    this.name = name;
    this.username = username;
    this.password = password;
    this.id = UUID.randomUUID().toString();
  }
}
