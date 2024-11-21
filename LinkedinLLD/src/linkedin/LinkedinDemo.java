package linkedin;

import java.util.List;
import linkedin.model.Company;
import linkedin.model.ConnectionRequest;
import linkedin.model.JobPost;
import linkedin.model.Message;
import linkedin.model.Skill;
import linkedin.model.User;

public class LinkedinDemo {

  public static void main(String[] args) {
    LinkedinService linkedinService = LinkedinService.getInstance();

    linkedinService.createProfile("mail1@gmail.com", "password1", "user1");
    linkedinService.createProfile("mail2@gmail.com", "password2", "user2");

    User user1 = linkedinService.login("mail1@gmail.com", "password1");
    User user2 = linkedinService.login("mail2@gmail.com", "password2");

    ConnectionRequest connectionRequest = linkedinService.sendRequest(user1, user2);
    linkedinService.acceptRequest(connectionRequest);

    Company company1 = linkedinService.createCompany("Google");

    JobPost post1 = linkedinService.addJobPosting(company1, "hiring software engineer", "Bengaluru", List.of(new Skill("Java"), new Skill("SQL")));

    List<User> userList = linkedinService.searchUser("user1");
    for(User user:userList) {
      System.out.println(user.name + " : " + user.profile.summary);
    }

    List<JobPost> postList = linkedinService.searchPost("software");
    for(JobPost jobpost:postList) {
      System.out.println(jobpost.company.name + " : " + jobpost.title);
    }

    linkedinService.sendMessage(user1, user2, "Hello there");

    for(Message message:linkedinService.getMessages(user2)) {
      System.out.println(message.content + " sent by :" + message.sender.name);
    }
  }
}
