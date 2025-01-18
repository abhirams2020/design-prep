import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

class Publisher {

  String id;

  public Publisher(String id) {
    this.id = id;
  }
}

class Subscriber {

  String id;

  public Subscriber(String id) {
    this.id = id;
  }
}

class Topic {

  String id;

  public Topic(String id) {
    this.id = id;
  }
}

class Message {

  String id;
  String content;
  String timestamp;

  public Message(String content) {
    this.id = UUID.randomUUID().toString();
    this.content = content;
    this.timestamp = LocalDateTime.now().toString();
  }

  public String getContent() {
    return content;
  }
}

class PublisherRepo {

  static Map<String, Publisher> publisherMap = new ConcurrentHashMap<>();
}

class SubscriberRepo {

  static Map<String, Subscriber> subscriberMap = new ConcurrentHashMap<>();
  static Map<String, Set<String>> subscriberTopicMap = new ConcurrentHashMap<>();
}

class TopicRepo {

  static Map<String, Topic> topicMap = new ConcurrentHashMap<>();
  static Map<String, Map<String, AtomicInteger>> offsetMap = new ConcurrentHashMap<>();
  static Map<String, List<Message>> topicMessageQueueMap = new ConcurrentHashMap<>();
}

class PublisherService {

  public static final PublisherService INSTANCE = new PublisherService();
  private final Broker broker = Broker.INSTANCE;

  private PublisherService() {
  }

  public void addPublisher(String id) {
    Publisher publisher = new Publisher(id);
    PublisherRepo.publisherMap.put(id, publisher);
  }

  public void publishMessage(String publisherId, Message message, String topicId) {
    if (!PublisherRepo.publisherMap.containsKey(publisherId)) {
      return;
    }
    broker.publish(publisherId, message, topicId);
  }
}

class SubscriberService {

  public static SubscriberService INSTANCE = new SubscriberService();
  private final Broker broker = Broker.INSTANCE;

  private SubscriberService() {
  }

  public void addSubscriber(String id) {
    Subscriber subscriber = new Subscriber(id);
    SubscriberRepo.subscriberMap.putIfAbsent(id, subscriber);
  }

  public void addTopic(String subscriberId, String topicId) {
    Set<String> topicSet = SubscriberRepo.subscriberTopicMap.getOrDefault(subscriberId,
        new HashSet<>());
    topicSet.add(topicId);
    SubscriberRepo.subscriberTopicMap.put(subscriberId, topicSet);
  }

  public Message consumeMessage(String subscriberId, String topicId) {
    return broker.consume(subscriberId, topicId);
  }

  public void pullMessages() {
    try {
      while (!Thread.currentThread().isInterrupted()) {
        for (String subscriber : SubscriberRepo.subscriberTopicMap.keySet()) {
          for (String topicId : SubscriberRepo.subscriberTopicMap.get(subscriber)) {
            Message message = broker.consume(subscriber, topicId);
            if (message == null) {
              continue;
            }
            System.out.println("received message : " + message.content + " at " + message.timestamp);
            Thread.sleep(500);
          }
        }
      }
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt(); // Restore interrupt flag
    }
  }
}

class Broker {

  public static final Broker INSTANCE = new Broker();

  private Broker() {
  }

  ;

  public void addTopic(String topicId) {
    Topic topic = new Topic(topicId);
    TopicRepo.topicMap.putIfAbsent(topicId, topic);
  }

  public synchronized void publish(String publisherId, Message message, String topicId) {
    TopicRepo.topicMessageQueueMap.putIfAbsent(topicId, new ArrayList<>());
    List<Message> messageList = TopicRepo.topicMessageQueueMap.get(topicId);
    messageList.add(message);
  }

  public Message consume(String subscriberId, String topicId) {
    TopicRepo.offsetMap.putIfAbsent(topicId, new ConcurrentHashMap<>());
    Map<String, AtomicInteger> topicOffsetMap = TopicRepo.offsetMap.get(topicId);
    topicOffsetMap.putIfAbsent(subscriberId, new AtomicInteger(0));
    AtomicInteger offset = topicOffsetMap.get(subscriberId);
    Message message = null;
    if (TopicRepo.topicMessageQueueMap.containsKey(topicId)) {
      List<Message> messageList
          = TopicRepo.topicMessageQueueMap.get(topicId);
      if (offset.get() < messageList.size()) {
        message = messageList.get(offset.get());
        offset.incrementAndGet();
        topicOffsetMap.put(subscriberId, offset);
      }
    }
    return message;
  }

  public void resetOffset(String subscriberId, String topicId, int offset) {
    Map<String, AtomicInteger> topicOffsetMap
        = TopicRepo.offsetMap.getOrDefault(topicId, new ConcurrentHashMap<>());
    topicOffsetMap.put(subscriberId, new AtomicInteger(offset));
  }
}

public class Main {

  public static void main(String[] args) {
    Broker broker = Broker.INSTANCE;
    broker.addTopic("topic1");
    broker.addTopic("topic2");

    SubscriberService subscriberService = SubscriberService.INSTANCE;

    subscriberService.addSubscriber("consumer1");
    subscriberService.addSubscriber("consumer2");
    subscriberService.addSubscriber("consumer3");
    subscriberService.addSubscriber("consumer4");
    subscriberService.addSubscriber("consumer5");

    subscriberService.addTopic("consumer1", "topic1");
    subscriberService.addTopic("consumer2", "topic1");
    subscriberService.addTopic("consumer3", "topic1");
    subscriberService.addTopic("consumer4", "topic1");
    subscriberService.addTopic("consumer5", "topic1");

    subscriberService.addTopic("consumer1", "topic2");
    subscriberService.addTopic("consumer3", "topic2");
    subscriberService.addTopic("consumer4", "topic2");

    PublisherService publisherService = PublisherService.INSTANCE;

    publisherService.addPublisher("producer1");
    publisherService.addPublisher("producer2");

    publisherService.publishMessage("producer1", new Message("message1"), "topic1");
    publisherService.publishMessage("producer1", new Message("message2"), "topic1");
    publisherService.publishMessage("producer1", new Message("message3"), "topic2");
    publisherService.publishMessage("producer2", new Message("message4"), "topic1");
    publisherService.publishMessage("producer2", new Message("message5"), "topic2");

    Runnable job = () -> {
      subscriberService.pullMessages();
    };

    Thread t1 = new Thread(job);
    t1.start();

    try {
      Thread.sleep(10000);
    } catch (Exception ignored) {
    }

    publisherService.publishMessage("producer1", new Message("message6"), "topic2");
    publisherService.publishMessage("producer2", new Message("message7"), "topic1");

    t1.interrupt();
  }
}