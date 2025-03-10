package instagramlld.service;

import instagramlld.enums.ContentType;
import instagramlld.model.Post;
import java.util.List;

public class InstagramService {

  private static volatile InstagramService instance = null;
  private UserService userService;
  private PostService postService;
  private FeedService feedService;

  public static InstagramService getInstance() {
    if(instance==null) {
      synchronized (InstagramService.class) {
        if(instance==null) {
          instance = new InstagramService();
        }
      }
    }
    return instance;
  }

  private InstagramService() {
    this.userService = UserService.getInstance();
    this.postService = PostService.getInstance();
    this.feedService = FeedService.getInstance();
  }

  public void register(String username, String password, String name) {
    userService.register(username, password, name);
  }

  public String login(String username, String password) {
    return userService.login(username, password);
  }

  public void logout(String username) {
    userService.logout(username);
  }

  public String addPost(String userId, String content, String imageUrl) {
    return postService.addPost(userId, content, imageUrl);
  }

  public List<Post> getFeed(String userId, int page, int limit) {
    return feedService.getFeed(userId, page, limit);
  }

  public void like(String userId, String contentId, ContentType contentType) {
    postService.like(userId,contentId,contentType);
  }

  public String addComment(String userId, String parentId, String content, ContentType parentContentType) {
    return postService.addComment(userId, parentId, content, parentContentType);
  }

  public void follow(String userId1, String userId2) {
    userService.follow(userId1, userId2);
  }
}
