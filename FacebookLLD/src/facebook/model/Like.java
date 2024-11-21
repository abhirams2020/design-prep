package facebook.model;

public class Like {
  public String id;
  public User user;

  public Like(String id, User user) {
    this.id = id;
    this.user = user;
  }
}
