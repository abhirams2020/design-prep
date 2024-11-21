package pubsub;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface Publisher {
  public void addTopic(Topic topic);
  public void removeTopic(Topic topic);
  public void publish(Topic topic, Message message);
}
