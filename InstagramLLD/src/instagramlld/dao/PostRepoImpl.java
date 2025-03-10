package instagramlld.dao;

import instagramlld.enums.ContentType;
import instagramlld.model.Comment;
import instagramlld.model.Like;
import instagramlld.model.Post;
import instagramlld.model.User;
import instagramlld.service.UserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PostRepoImpl {
  private Map<String, Post> postMap;
  private Map<String, Comment> commentMap;
  private Map<String, List<String>> commentParentMap; // mp[parentid] = commentid
  private Map<String, Set<String>> userfeedMap;
  private Map<String, Map<String, Like>> likeMap; // mp[content][user] = like

  private PostRepoImpl() {
    this.postMap = new HashMap<>();
    this.commentMap = new HashMap<>();
    this.userfeedMap = new HashMap<>();
    this.commentParentMap = new HashMap<>();
    this.likeMap = new HashMap<>();
  }

  public static volatile PostRepoImpl instance = null;

  public static PostRepoImpl getInstance() {
    if(instance==null) {
      synchronized (PostRepoImpl.class) {
        if(instance==null) {
          instance = new PostRepoImpl();
        }
      }
    }
    return instance;
  }

  public String addPost(String userId, String content, String imageUrl) {
    Post post = new Post(userId, content, imageUrl);
    postMap.put(post.getId(), post);
    return post.getId();
  }

  public void like(String userId, String contentId, ContentType contentType) {
    likeMap.putIfAbsent(contentId, new HashMap<>());
    likeMap.get(contentId).putIfAbsent(userId, new Like(userId, contentId));
  }

  public String addComment(String userId, String parentId, String content, ContentType parentContentType) {
    // validate parent exist
    Comment comment = new Comment(userId, parentId, content);
    commentMap.put(comment.getId(), comment);
    List<String> childList = commentParentMap.getOrDefault(parentId, new ArrayList<>());
    childList.add(comment.getId());
    commentParentMap.put(parentId, childList);
    return comment.getId();
  }

  public Post getPostById(String id) {
    return postMap.get(id);
  }

  public int getLikesCount(String contentId) {
    return likeMap.getOrDefault(contentId, new HashMap<>()).size();
  }

  public List<Comment> getComments(String parentId) {
    List<Comment> res = new ArrayList<>();
    for(String commentId:commentParentMap.getOrDefault(parentId, new ArrayList<>())) {
      res.add(commentMap.get(commentId));
    }
    return res;
  }

  public int getCommentCount(String userId, String contentId) {
    if(!commentParentMap.containsKey(contentId)) {
      return 0;
    }
    int count = 1;
    for(String childId:commentParentMap.get(contentId)) {
      count += getCommentCount(userId, childId);
    }
    return count;
  }

  public List<Post> getFeed(String userId) {
    List<Post> res = new ArrayList<>();
    for(String postId:userfeedMap.getOrDefault(userId, new HashSet<>())) {
      res.add(postMap.get(postId));
    }
    return res;
  }

  public void addPostToFeed(String userId, String postId) {
    userfeedMap.putIfAbsent(userId, new HashSet<>());
    userfeedMap.get(userId).add(postId);
  }
}
