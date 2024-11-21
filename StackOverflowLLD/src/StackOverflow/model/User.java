package StackOverflow.model;

public class User {
  public String id;
  public String name;
  public Long reputationScore;

  public User(String id, String name) {
    this.id = id;
    this.name = name;
    this.reputationScore = 0L;
  }
}
