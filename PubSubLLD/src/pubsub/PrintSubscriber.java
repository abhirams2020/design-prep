package pubsub;

public class PrintSubscriber implements Subscriber{

  Message message;

  public PrintSubscriber() {
    this.message = new Message("");
  }

  @Override
  public void update(Message message) {
    this.message = message;
  }

  @Override
  public void display() {
    System.out.println("message : " + message.getContent());
  }
}
