package facebook.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Comment {
  public String commentId;
  public User user;
  public int timestamp;
  public String commentContent;
  public List<Like> likeList;

  public Comment(String commentId, User user, String commentContent, int timestamp) {
    this.commentId = commentId;
    this.user = user;
    this.commentContent = commentContent;
    this.likeList = new ArrayList<>();
    this.timestamp = timestamp;
  }
}
