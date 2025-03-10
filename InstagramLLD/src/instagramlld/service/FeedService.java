package instagramlld.service;

import instagramlld.dao.PostRepoImpl;
import instagramlld.dao.UserRepoImpl;
import instagramlld.model.Post;
import java.util.ArrayList;
import java.util.List;

public class FeedService {
  private static volatile FeedService instance = null;

  public static FeedService getInstance() {
    if(instance==null) {
      synchronized (FeedService.class) {
        if(instance==null) {
          instance = new FeedService();
        }
      }
    }
    return instance;
  }

  UserRepoImpl userRepo;
  PostRepoImpl postRepo;

  private FeedService() {
    userRepo = UserRepoImpl.getInstance();
    postRepo = PostRepoImpl.getInstance();
  }

  public List<Post> getFeed(String userId, int page, int limit) {
    return postRepo.getFeed(userId);
  }

  public void populateFeed(String userId, String postId) {
    postRepo.addPostToFeed(userId, postId);
    List<String> followerList = userRepo.getFollowers(userId);
    for(String follower:followerList) {
      postRepo.addPostToFeed(follower, postId);
    }
  }
}
