package facebook.service;

import facebook.model.Comment;
import facebook.model.FriendRequest;
import facebook.model.Like;
import facebook.model.Post;
import facebook.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class FacebookService {

  private static final int MAX_POST_COUNT = 10;
  public List<User> userList;
  public Map<User, List<FriendRequest>> friendReqMap;

  private static FacebookService instance = new FacebookService();

  public static FacebookService getInstance() {
    return instance;
  }

  private FacebookService() {
    this.userList = new ArrayList<>();
    this.friendReqMap = new HashMap<>();
  }


  public void createAccount(String mail, String password, String name) {
    if(checkUserExist(mail)) {
      return;
    }
    User user = new User(mail, password, name);
    userList.add(user);
  }

  public User login(String mail, String password) {
    return getUser(mail, password);
  }

  public void sendFriendRequest(FriendRequest friendRequest) {
    List<FriendRequest> friendReqList = friendReqMap.getOrDefault(friendRequest.toUser, new ArrayList<>());
    friendReqList.add(friendRequest);
    friendReqMap.put(friendRequest.toUser, friendReqList);
  }

  public void acceptRequest(User user, FriendRequest friendRequest) {
    for(FriendRequest request:friendReqMap.get(user)) {
      if(request.equals(friendRequest)) {
        friendRequest.toUser.friendList.add(friendRequest.fromUser);
        friendRequest.fromUser.friendList.add(friendRequest.toUser);
        return;
      }
    }
  }

  public Post addPost(User user, String postContent, int timestamp) {
    Post post = new Post("1", user, postContent, timestamp);
    user.postList.add(post);
    notifyFriends(user, post);
    return post;
  }

  private void notifyFriends(User user, Post post) {
    for(User friend:user.friendList) {
      friend.addToFeed(post);
    }
  }

  public Comment addComment(User user, Post post, String commentContent, int timestamp) {
    Comment comment = new Comment("1", user, commentContent, timestamp);
    post.commentList.add(comment);
    return comment;
  }

  public void likePost(User user, Post post) {
    post.likeList.add(new Like("1", user));
  }

  public void displayFeed(User user) {
    System.out.println("Showing user feed for " + user.name);
    for(Post post:generateFeed(user)) {
      System.out.println(post.getPostString());
    }
  }

  private boolean checkUserExist(String mail) {
    for(User user:userList) {
      if(user.emailId == mail) {
        return true;
      }
    }
    return false;
  }

  private User getUser(String mail, String password) {
    for(User user:userList) {
      if(user.emailId.equals(mail) && user.password.equals(password)) {
        return user;
      }
    }
    return null;
  }

  private List<Post> generateFeed(User user) {
    PriorityQueue<Post> feed = new PriorityQueue<>((p1, p2) -> p2.timestamp - p1.timestamp);
    for(int i=0; i<user.postList.size(); i++) {
      feed.offer(user.postList.get(i));
      if (feed.size() > MAX_POST_COUNT) {
        feed.poll();
      }
    }
    for(User friend:user.friendList) {
      for(Post post: friend.postList) {
        feed.offer(post);
        if (feed.size() > MAX_POST_COUNT) {
          feed.poll();
        }
      }
    }
    return new ArrayList<>(feed);
  }
}
