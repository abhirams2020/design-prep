package StackOverflow.service;

import StackOverflow.enums.TagType;
import StackOverflow.model.Answer;
import StackOverflow.model.Comment;
import StackOverflow.model.Question;
import StackOverflow.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StackOverflowService {

  private List<User> userList;
  private Map<User, Question> userQuestionMap;
  private List<Question> questionList;
  private Set<String> upvoteSet;
  private Set<String> downvoteSet;

  public static StackOverflowService instance = new StackOverflowService();

  private StackOverflowService() {
    userList = new ArrayList<>();
    userQuestionMap = new HashMap<>();
//    userAnswerMap = new HashMap<>();
//    userCommentMap = new HashMap<>();
    upvoteSet = new HashSet<>();
    downvoteSet = new HashSet<>();
    questionList = new ArrayList<>();
  }

  public Question askQuestion(String id, User user, String questionText, TagType tagType) {
    Question question = new Question(id, user.id, questionText, tagType);
    userQuestionMap.put(user, question);
    questionList.add(question);
    return question;
  }

  public Comment addQuestionComment(String id, Question question, User user, String commentText) {
    Comment comment = new Comment(id, user.id, commentText);
    question.commentList.add(comment);
    return comment;
  }

  public Answer answerQuestion(String id, Question question, User user, String answerText) {
    Answer answer = new Answer(id, user.id, answerText);
    question.answerList.add(answer);
    return answer;
  }

  public Comment addAnswerComment(String id, Answer answer, User user, String commentText) {
    Comment comment = new Comment(id, user.id, commentText);
    answer.commentList.add(comment);
    return comment;
  }

  public void upvote(Question question, User user) {
    String key = "question_" + question.id + "_" + user.id;
    if(upvoteSet.contains(key)) {
      return;
    }
    if(downvoteSet.contains(key)) {
      downvoteSet.remove(key);
      question.votes++;
    }
    upvoteSet.add(key);
    question.votes++;
  }

  public void upvote(Answer answer, User user) {
    String key = "answer_" + answer.id + "_" + user.id;
    if(upvoteSet.contains(key)) {
      return;
    }
    if(downvoteSet.contains(key)) {
      downvoteSet.remove(key);
      answer.votes++;
    }
    upvoteSet.add(key);
    answer.votes++;
  }

  public void upvote(Comment comment, User user) {
    String key = "comment_" + comment.id + "_" + user.id;
    if(upvoteSet.contains(key)) {
      return;
    }
    if(downvoteSet.contains(key)) {
      downvoteSet.remove(key);
      comment.votes++;
    }
    upvoteSet.add(key);
    comment.votes++;
  }

  public void downvote(Question question, User user) {
    String key = "question_" + question.id + "_" + user.id;
    if(downvoteSet.contains(key)) {
      return;
    }
    if(upvoteSet.contains(key)) {
      upvoteSet.remove(key);
      question.votes--;
    }
    downvoteSet.add(key);
    question.votes--;
  }

  public void downvote(Answer answer, User user) {
    String key = "answer_" + answer.id + "_" + user.id;
    if(downvoteSet.contains(key)) {
      return;
    }
    if(upvoteSet.contains(key)) {
      upvoteSet.remove(key);
      answer.votes--;
    }
    downvoteSet.add(key);
    answer.votes--;
  }

  public void downvote(Comment comment, User user) {
    String key = "comment_" + comment.id + "_" + user.id;
    if(downvoteSet.contains(key)) {
      return;
    }
    if(upvoteSet.contains(key)) {
      upvoteSet.remove(key);
      comment.votes--;
    }
    downvoteSet.add(key);
    comment.votes--;
  }


  public List<Question> searchByTag(TagType tagType) {
    List<Question> returnList = new ArrayList<>();
    for(Question question:questionList) {
      if(question.tagType.equals(tagType)){
        returnList.add(question);
      }
    }
    return returnList;
  }

  public void displayPost(Question question) {
    System.out.println("Post " + question.id);
    System.out.println(question.questionText + " asked by " + getUserById(question.userId));
    System.out.println("\t votes :" + question.votes);
    for(Comment comment: question.commentList) {
      System.out.println(comment.commentText + " commented by " + getUserById(comment.userId));
      System.out.println("\t votes :" + comment.votes);
    }

    for(Answer answer: question.answerList) {
      System.out.println(answer.answerText + " answered by " + getUserById(answer.userId));
      System.out.println("\t votes :" + answer.votes);
      for(Comment comment: answer.commentList) {
        System.out.println("\t" + comment.commentText + " commented by " + getUserById(comment.userId));
        System.out.println("\t votes :" + comment.votes);
      }
    }
  }

  public User createUser(String id, String name) {
    User user = new User(id, name);
    userList.add(user);
    return user;
  }

  public User getUserById(String id) {
    for(User user:userList) {
      if(user.id.equals(id)) {
        return user;
      }
    }
    return null;
  }
}
