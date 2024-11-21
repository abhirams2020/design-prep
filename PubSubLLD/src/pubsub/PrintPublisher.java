package pubsub;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class PrintPublisher implements Publisher{

  Set<Topic> topicSet;

  public PrintPublisher() {
    topicSet = new CopyOnWriteArraySet<>();
  }

  @Override
  public void addTopic(Topic topic) {
    topicSet.add(topic);
  }

  @Override
  public void removeTopic(Topic topic) {
    topicSet.remove(topic);
  }

  @Override
  public void publish(Topic topic, Message message) {
    if(!topicSet.contains(topic)) {
      System.out.println("topic not found in publisher");
      return;
    }
    topic.publish(message);
  }
}
