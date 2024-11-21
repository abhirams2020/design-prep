package facebook.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class User {
  private static final int MAX_FEED_SIZE = 20;

  public String emailId, password, name, profilePicUrl;
  public List<Post> postList;
  public List<User> friendList;
  public Deque<Post> feedList;

  public User(String emailId, String password, String name) {
    this.emailId = emailId;
    this.password = password;
    this.name = name;
    this.postList = new ArrayList<>();
    this.friendList = new ArrayList<>();
    this.feedList = new ArrayDeque<>();
  }

  public void addToFeed(Post post) {
    feedList.addFirst(post);
    if(feedList.size() > MAX_FEED_SIZE) {
      feedList.removeLast();
    }
  }

  public void removeFromFeed(Post post) {
    feedList.remove(post);
  }
}
