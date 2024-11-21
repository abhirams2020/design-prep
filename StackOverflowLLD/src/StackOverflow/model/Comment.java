package StackOverflow.model;

public class Comment {
  public String id;
  public String userId;
  public String commentText;
  public Long votes;

  public Comment(String id, String userId, String commentText) {
    this.id = id;
    this.userId = userId;
    this.commentText = commentText;
    votes = 0L;
  }
}
