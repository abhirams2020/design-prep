package linkedin.model;

public class ConnectionRequest {
  public String id;
  public User sender;
  public User receiver;

  public ConnectionRequest(String id, User sender, User receiver) {
    this.id = id;
    this.sender = sender;
    this.receiver = receiver;
  }
}
