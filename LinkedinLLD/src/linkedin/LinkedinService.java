package linkedin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import linkedin.model.Company;
import linkedin.model.ConnectionRequest;
import linkedin.model.JobPost;
import linkedin.model.Message;
import linkedin.model.Skill;
import linkedin.model.User;

public class LinkedinService {

  private static final LinkedinService instance = new LinkedinService();
  private List<User> userList;
  private List<JobPost> jobPostList;
  private Map<User, List<ConnectionRequest>> requestMap;
  private Map<User, List<User>> friendMap;
  private Map<User, List<Message>> messageMap;

  public static LinkedinService getInstance() {
    return instance;
  }

  private LinkedinService() {
    this.userList = new ArrayList<>();
    this.jobPostList = new ArrayList<>();
    this.requestMap = new HashMap<>();
    this.friendMap = new HashMap<>();
    this.messageMap = new HashMap<>();
  }


  public void createProfile(String mail, String password, String name) {
    if(checkUserExist(mail) == null){
      User user = new User(mail, password, name);
      userList.add(user);
    }
  }

  private User checkUserExist(String mail) {
    for(User user:userList) {
      if(user.email.equals(mail)) {
        return user;
      }
    }
    return null;
  }

  public User login(String mail, String password) {
    User user = checkUserExist(mail);
    if(user!=null && user.password.equals(password)) {
      return user;
    }
    return null;
  }

  public ConnectionRequest sendRequest(User sender, User receiver) {
    ConnectionRequest request = new ConnectionRequest(generateId(), sender, receiver);
    List<ConnectionRequest> requestList = requestMap.getOrDefault(receiver, new ArrayList<>());
    requestList.add(request);
    requestMap.put(receiver, requestList);
    return request;
  }

  public void acceptRequest(ConnectionRequest connectionRequest) {
    User receiver = connectionRequest.receiver;
    List<ConnectionRequest> requestList = requestMap.getOrDefault(receiver, new ArrayList<>());
    for(ConnectionRequest request:requestList) {
      if(request.equals(connectionRequest)) {
        friendMap.getOrDefault(connectionRequest.receiver, new ArrayList<>()).add(connectionRequest.sender);
        friendMap.getOrDefault(connectionRequest.sender, new ArrayList<>()).add(connectionRequest.receiver);
        return;
      }
    }
  }

  public Company createCompany(String companyName) {
    Company company = new Company(generateId(), companyName);
    return company;
  }

  public JobPost addJobPosting(Company company, String title, String location, List<Skill> skills) {
    JobPost jobPost = new JobPost(generateId(), company, title, location, skills);
    jobPostList.add(jobPost);
    return jobPost;
  }

  public List<User> searchUser(String username) {
    List<User> validUserList = new ArrayList<>();
    for(User user:userList) {
      if(user.name.contains(username)) {
        validUserList.add(user);
      }
    }
    return validUserList;
  }

  public List<JobPost> searchPost(String searchString) {
    List<JobPost> validPostList = new ArrayList<>();
    for(JobPost post:jobPostList) {
      if(post.title.contains(searchString)) {
        validPostList.add(post);
      }
    }
    return validPostList;
  }

  public Message sendMessage(User sender, User receiver, String content) {
    Message message = new Message(generateId(), sender, receiver, content);
    List<Message> messageList = messageMap.getOrDefault(receiver, new ArrayList<>());
    messageList.add(message);
    messageMap.put(receiver, messageList);
    return message;
  }

  public List<Message> getMessages(User user) {
    return messageMap.getOrDefault(user, new ArrayList<>());
  }

  private String generateId() {
    return UUID.randomUUID().toString();
  }
}
