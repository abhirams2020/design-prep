package StackOverflow;

import StackOverflow.enums.TagType;
import StackOverflow.model.Answer;
import StackOverflow.model.Comment;
import StackOverflow.model.Question;
import StackOverflow.model.User;
import StackOverflow.service.StackOverflowService;
import java.util.List;

public class StackOverflowDemo {

  public static void main(String[] args) {
    StackOverflowService stackOverflowService = StackOverflowService.instance;

    User user1 = stackOverflowService.createUser("1", "Abhiram");
    User user2 = stackOverflowService.createUser("2", "Cristiano");
    User user3 = stackOverflowService.createUser("3", "Sachin");
    User user4 = stackOverflowService.createUser("4", "Napoleon");

    Question question1 = stackOverflowService.askQuestion("1", user1, "What is JVM in Java?", TagType.JAVA);
    Comment comment1 = stackOverflowService.addQuestionComment("2", question1, user2, "Good question.");

    Answer answer1 = stackOverflowService.answerQuestion("3", question1, user3, "JVM is abcdefg");
    Comment comment2 = stackOverflowService.addAnswerComment("4", answer1, user4, "Good answer.");

    stackOverflowService.upvote(question1, user2);
    stackOverflowService.upvote(answer1, user1);
    stackOverflowService.upvote(comment1, user3);

    stackOverflowService.displayPost(question1);

    stackOverflowService.downvote(question1, user4);
    stackOverflowService.downvote(question1, user3);
    stackOverflowService.downvote(question1, user3);
    stackOverflowService.downvote(question1, user3);
    stackOverflowService.downvote(question1, user3);
    stackOverflowService.downvote(question1, user3);
    stackOverflowService.downvote(question1, user3);

    stackOverflowService.displayPost(question1);

    List<Question> questionList = stackOverflowService.searchByTag(TagType.JAVA);

    System.out.println(questionList.toString());
  }
}
