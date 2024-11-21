package pubsub;

public class PubSubDemo {

  public static void main(String[] args) {
    System.out.println("hello world");

    Topic topic1 = new Topic();
    Topic topic2 = new Topic();

    Subscriber sub1 = new PrintSubscriber();
    Subscriber sub2 = new PrintSubscriber();

    Publisher pub1 = new PrintPublisher();

    pub1.addTopic(topic1);
    pub1.addTopic(topic2);

    topic1.addSubscriber(sub1);
    topic2.addSubscriber(sub2);

    pub1.publish(topic1, new Message("topic 1 message updated"));
    pub1.publish(topic2, new Message("topic 2 message updated"));

    sub1.display();
    sub2.display();
  }
}
