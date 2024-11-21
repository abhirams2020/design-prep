package StackOverflow.model;

import java.util.ArrayList;
import java.util.List;

public class Answer {
  public String id;
  public String userId;
  public String answerText;
  public Long votes;
  public List<Comment> commentList;

  public Answer(String id, String userId, String answerText) {
    this.id = id;
    this.userId = userId;
    this.answerText = answerText;
    votes = 0L;
    commentList = new ArrayList<>();
  }
}
