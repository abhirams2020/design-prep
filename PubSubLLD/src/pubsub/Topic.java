package pubsub;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class Topic {

  Set<Subscriber> subscriberSet;

  public Topic() {
    subscriberSet = new CopyOnWriteArraySet<>();
  }

  public void addSubscriber(Subscriber subscriber) {
    subscriberSet.add(subscriber);
  }

  public void removeSubscriber(Subscriber subscriber) {
    subscriberSet.remove(subscriber);
  }

  public void publish(Message message) {
    for(Subscriber subscriber : subscriberSet) {
      subscriber.update(message);
    }
  }
}
