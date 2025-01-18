import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.Callable;

interface LimitStrategy {
  boolean allowRequest(String id);
}

class TokenBucket {
  String id;
  final int bucketSize;
  int tokenCount;
  long nextRefillTime;
  final long refillWindow;

  public TokenBucket(int bucketSize, long refillWindow) {
    this.id = UUID.randomUUID().toString();
    this.bucketSize = bucketSize;
    this.tokenCount = bucketSize;
    this.refillWindow = refillWindow;
    this.nextRefillTime = System.currentTimeMillis() + refillWindow;
  }
}

class TokenBucketStrategy implements LimitStrategy {
  TokenBucket tokenBucket;

  public TokenBucketStrategy(int bucketSize, long refillWindow) {
    this.tokenBucket = new TokenBucket(bucketSize, refillWindow);
  }

  private boolean checkRefill() {
    if(System.currentTimeMillis() >= tokenBucket.nextRefillTime) {
      tokenBucket.tokenCount = tokenBucket.bucketSize;
      tokenBucket.nextRefillTime = System.currentTimeMillis() + tokenBucket.refillWindow;
      return true;
    }
    return false;
  }
  @Override
  public boolean allowRequest(String id) {
    checkRefill();
    if(tokenBucket.tokenCount > 0) {
      tokenBucket.tokenCount-=1;
      return true;
    }
    return false;
  }
}

class RateLimiter {
  LimitStrategy limitStrategy;

  public RateLimiter(LimitStrategy limitStrategy) {
    this.limitStrategy = limitStrategy;
  }

  public boolean allowRequest(String id) {
    return limitStrategy.allowRequest(id);
  }
}

class BookingService {
  RateLimiter rateLimiter;

  public BookingService(LimitStrategy limitStrategy) {
    rateLimiter = new RateLimiter(limitStrategy);
  }

  public void book(String id) {
    if(!rateLimiter.allowRequest(id)) {
      System.out.println("request limit exceeded for user : " + id);
    } else {
      System.out.println("request successful for user : " + id);
    }
  }
}

public class Main {

  public static void main(String[] args) {
    BookingService bookingService = new BookingService(new TokenBucketStrategy(5, 10000));

    Thread t1 = new Thread(()-> {
      while(true) {
        bookingService.book("id1");
        try {
          Thread.sleep(1000);
        } catch (Exception e) {
          // do nothing
        }
      }
    });

    t1.start();
  }
}