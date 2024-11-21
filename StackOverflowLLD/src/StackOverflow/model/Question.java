package StackOverflow.model;

import StackOverflow.enums.TagType;
import java.util.ArrayList;
import java.util.List;

public class Question {
  public String id;
  public String userId;
  public String questionText;
  public Long votes;
  public TagType tagType;
  public List<Answer> answerList;
  public List<Comment> commentList;

  public Question(String id, String userId, String questionText, TagType tagType) {
    this.id = id;
    this.userId = userId;
    this.questionText = questionText;
    this.tagType = tagType;
    votes = 0L;
    answerList = new ArrayList<>();
    commentList = new ArrayList<>();
  }
}
