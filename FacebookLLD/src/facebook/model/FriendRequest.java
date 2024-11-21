package facebook.model;

public class FriendRequest {
  public String id;
  public User fromUser;
  public User toUser;

  public FriendRequest(String id, User fromUser, User toUser) {
    this.id = id;
    this.fromUser = fromUser;
    this.toUser = toUser;
  }
}
