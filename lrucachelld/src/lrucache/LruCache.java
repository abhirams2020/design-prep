package lrucache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LruCache<K,V> {

  private Map<K, Node<K, V>> map;
  // for high reads, use concurrentLinkedDeque instead of nodes
  private final Node<K, V> head, tail;
  private final int capacity;
  private final ReadWriteLock lock = new ReentrantReadWriteLock(true); // fair lock
  private final Lock readLock = lock.readLock();
  private final Lock writeLock = lock.writeLock();
  private final ExecutorService executorService;

  public LruCache(int capacity) {
    this.capacity = capacity;
    this.map = new ConcurrentHashMap<>();
    this.head = new Node<>();
    this.tail = new Node<>();
    this.executorService = Executors.newFixedThreadPool(2);
    head.next = tail;
    tail.prev = head;
  }

  public V get(K key) {
    Node<K, V> node;
    // Concurrent read from the map
    node = map.get(key);
    if (node == null) {
      return null;
    }

    /*
    // updating linked list async using background threads
    final Node<K, V> finalNode = node;
    executorService.submit(() -> {
      writeLock.lock();
      try {
        moveToFront(finalNode);
      } finally {
        writeLock.unlock();
      }
    });
    */

    // same thread handling linked list update
    writeLock.lock();
    try {
      moveToFront(node); // Safely modify the LRU order
    } finally {
      writeLock.unlock(); // Always release the write lock
    }

    return node.value;
  }

  public void put(K key, V value) {
    writeLock.lock();
    try {
      if (map.containsKey(key)) {
        Node<K, V> node = map.get(key);
        node.value = value;
        moveToFront(node);
      } else {
        Node<K, V> newNode = new Node<>(key, value);
        map.put(key, newNode);
        addToFront(newNode);
        if (map.size() > capacity) {
          removeLast();
        }
      }
    } finally {
      writeLock.unlock();
    }
  }

  public void delete(K key) {
    writeLock.lock();
    try {
      Node<K, V> node = map.get(key);
      if (node != null) {
        removeNode(node);
        map.remove(key);
      }
    } finally {
      writeLock.unlock();
    }
  }

  private void moveToFront(Node<K, V> node) {
    removeNode(node);
    addToFront(node);
  }

  private void addToFront(Node<K, V> node) {
    node.next = head.next;
    node.prev = head;
    head.next.prev = node;
    head.next = node;
  }

  private void removeNode(Node<K, V> node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;
  }

  private void removeLast() {
    if (head.next == tail) return;
    Node<K, V> nodeToRemove = tail.prev;
    map.remove(nodeToRemove.key);
    removeNode(nodeToRemove);
  }

  public void printCache() {
    System.out.println("printing started");
    Node<K,V> cur = head.next;
    while(cur != tail) {
      System.out.println("key:" + cur.key + ", value:" + cur.value);
      cur = cur.next;
    }
    System.out.println("printing done");
  }
}
