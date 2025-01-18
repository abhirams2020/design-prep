package lrucache;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LruCacheTest {

  private LruCache<Integer, Integer> lruCache;

  private LruCache<Integer,Integer> initializeCache() {
    lruCache = new LruCache<>(5);
    lruCache.put(1,1);
    lruCache.put(2,2);
    lruCache.put(3,3);
    lruCache.put(4,4);
    lruCache.put(5,5);
    return lruCache;
  }

  @Test
  void get() {
    lruCache = initializeCache();
    lruCache.printCache();
    Integer value = lruCache.get(2);
    lruCache.printCache();
    Assertions.assertEquals(2, value);
  }

  @Test
  void getInvalid() {
    lruCache = initializeCache();
    Integer value = lruCache.get(10);
    Assertions.assertNull(value);
  }

  @Test
  void put() {
    lruCache = initializeCache();
    lruCache.put(10,10);
    lruCache.printCache();
    Assertions.assertNull(lruCache.get(1));
  }

  @Test
  void delete() {
    lruCache = initializeCache();
    lruCache.delete(3);
    lruCache.printCache();
    Assertions.assertNull(lruCache.get(3));
  }
}