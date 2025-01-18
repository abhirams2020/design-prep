public class Test {

  public void TestBroker() {
    Broker broker = Broker.INSTANCE;
    broker.addTopic("topic1");
    broker.addTopic("topic2");
  }
}
