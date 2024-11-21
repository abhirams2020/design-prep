package pubsub;

public interface Subscriber {
  public void update(Message message);
  public void display();
}
