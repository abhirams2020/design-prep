package linkedin.model;

public class Message {
  public String id;
  public User sender;
  public User receiver;
  public String content;

  public Message(String id, User sender, User receiver, String content) {
    this.id = id;
    this.sender = sender;
    this.receiver = receiver;
    this.content = content;
  }
}
