package instagramlld.service;

import instagramlld.dao.PostRepoImpl;
import instagramlld.dao.UserRepoImpl;
import instagramlld.enums.ContentType;
import instagramlld.model.Post;

public class PostService {
  private static volatile PostService instance = null;

  public static PostService getInstance() {
    if(instance==null) {
      synchronized (PostService.class) {
        if(instance==null) {
          instance = new PostService();
        }
      }
    }
    return instance;
  }

  UserRepoImpl userRepo;
  PostRepoImpl postRepo;

  private PostService() {
    userRepo = UserRepoImpl.getInstance();
    postRepo = PostRepoImpl.getInstance();
  }

  public String addPost(String userId, String content, String imageUrl) {
    validateUser(userId);
    String postId = postRepo.addPost(userId, content, imageUrl);
    FeedService.getInstance().populateFeed(userId, postId);
    return postId;
  }

  public void like(String userId, String contentId, ContentType contentType) {
    validateUser(userId);
    postRepo.like(userId, contentId, contentType);
  }

  public String addComment(String userId, String parentId, String content, ContentType parentContentType) {
    validateUser(userId);
    return postRepo.addComment(userId, parentId, content, parentContentType);
  }

  private void validateUser(String userId) {
    if(!userRepo.isLoggedIn(userId)) {
      throw new IllegalArgumentException("user not logged in");
    }
  }
}
