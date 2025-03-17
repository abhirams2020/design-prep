package SearchEngine.models;

import java.time.LocalDateTime;
import java.util.UUID;

public class User {
  String id, name, createdAt;

  public User(String name) {
    this.name = name;
    this.id = UUID.randomUUID().toString();
    this.createdAt = LocalDateTime.now().toString();
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCreatedAt() {
    return createdAt;
  }
}
