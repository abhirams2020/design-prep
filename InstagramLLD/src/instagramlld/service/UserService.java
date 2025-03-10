package instagramlld.service;

import instagramlld.dao.UserRepoImpl;

public class UserService {
  private static volatile UserService instance = null;

  public static UserService getInstance() {
    if(instance==null) {
      synchronized (UserService.class) {
        if(instance==null) {
          instance = new UserService();
        }
      }
    }
    return instance;
  }

  UserRepoImpl userRepo;

  private UserService() {
    userRepo = UserRepoImpl.getInstance();
  }

  public void register(String username, String password, String name) {
    userRepo.register(username, password, name);
  }

  public String login(String username, String password) {
    return userRepo.login(username, password);
  }

  public void logout(String userId) {
    userRepo.logout(userId);
  }

  public void follow(String userId1, String userId2) {
    userRepo.follow(userId1, userId2);
  }
}
