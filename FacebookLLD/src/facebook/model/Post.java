package facebook.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post {
  public String id;
  public User user;
  public int timestamp;
  public String postContent;
  public List<Comment> commentList;
  public List<Like> likeList;

  public Post(String id, User user, String postContent, int timestamp) {
    this.id = id;
    this.user = user;
    this.postContent = postContent;
    this.commentList = new ArrayList<>();
    this.likeList = new ArrayList<>();
    this.timestamp = timestamp;
  }

  public String getPostString() {
    String content = "post by : " + user.name + " => " + postContent + " with likes : " + likeList.size() + "\n";
    for(Comment comment:commentList) {
      content += "\t comment by : " + comment.user.name + " => " + comment.commentContent + "\n";
    }
    return content;
  }
}
