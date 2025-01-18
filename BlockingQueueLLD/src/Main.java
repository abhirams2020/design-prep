import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class BlockingQueueException extends RuntimeException {
  public BlockingQueueException(String message) {
    super(message);
  }
}

class Producer<V> {
  String id;
  BlockingQueue<V> blockingQueue;

  public Producer(String id, BlockingQueue<V> blockingQueue) {
    this.id = id;
    this.blockingQueue = blockingQueue;
  }

  public void putTask(V v) {
    blockingQueue.put(v);
    System.out.println("added task : " + v);
  }
}

class Consumer<V> {
  String id;
  BlockingQueue<V> blockingQueue;

  public Consumer(String id, BlockingQueue<V> blockingQueue) {
    this.id = id;
    this.blockingQueue = blockingQueue;
  }

  public void getTask() {
    V task  = blockingQueue.get();
    System.out.println("received task : " + task);
  }
}

class BlockingQueue<V> {
  int maxSize;
  List<V> queue = new LinkedList<>();

  private final ReentrantLock lock = new ReentrantLock();
  private final Condition notFull = lock.newCondition();
  private final Condition notEmpty = lock.newCondition();

  public BlockingQueue(int maxSize) {
    this.maxSize = maxSize;
  }

  public void put(V v) {
    lock.lock();
    try {
      while(queue.size()==maxSize) {
        notFull.await();
      }
      queue.addLast(v);
      notEmpty.signalAll();
    } catch (InterruptedException e) {
      throw new BlockingQueueException("put method failed");
    } finally {
      lock.unlock();
    }
  }

  public V get() {
    lock.lock();
    try {
      while(queue.size()==0) {
        notEmpty.await();
      }
      V val = queue.removeFirst();
      notFull.signalAll();
      return val;
    } catch (Exception e) {
      throw new BlockingQueueException("get method failed");
    } finally {
      lock.unlock();
    }
  }
}

class RunnableProducer<V> implements Runnable {
  Producer<V> producer;
  public RunnableProducer(Producer<V> producer) {
    this.producer = producer;
  }
  @Override
  public void run() {

  }
}

public class Main {
  public static void main(String[] args) {
    BlockingQueue<Integer> blockingQueue = new BlockingQueue<>(5);
    Producer<Integer> producer1 = new Producer<>("producer1", blockingQueue);
    Producer<Integer> producer2 = new Producer<>("producer2", blockingQueue);
    Producer<Integer> producer3 = new Producer<>("producer3", blockingQueue);
    Consumer<Integer> consumer1 = new Consumer<>("consumer1", blockingQueue);
    Consumer<Integer> consumer2 = new Consumer<>("consumer2", blockingQueue);
    Consumer<Integer> consumer3 = new Consumer<>("consumer3", blockingQueue);

    Thread t1 = new Thread(()->{
      for(Producer<Integer> producer:List.of(producer1,producer2,producer3)) {
        producer.putTask(ThreadLocalRandom.current().nextInt());
      }
    });

    Thread t2 = new Thread(()->{
      for(Consumer<Integer> consumer:List.of(consumer1,consumer2,consumer3)) {
        consumer.getTask();
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    });

    t1.start();
    t2.start();
  }
}