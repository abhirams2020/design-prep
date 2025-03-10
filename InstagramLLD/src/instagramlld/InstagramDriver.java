package instagramlld;

import instagramlld.enums.ContentType;
import instagramlld.model.Post;
import instagramlld.service.InstagramService;
import instagramlld.utiil.FeedPrinterUtil;
import java.util.List;

public class InstagramDriver {

  public static void main(String[] args) {
    // register and login user
    InstagramService instagramService = InstagramService.getInstance();

    instagramService.register("abhirams2020@gmail.com", "12345", "abhiram s");
    instagramService.register("abhiram@gmail.com", "12345", "s abhiram");

    String userId1 = instagramService.login("abhirams2020@gmail.com", "12345");
    String userId2 = instagramService.login("abhiram@gmail.com", "12345");

    instagramService.follow(userId1, userId2);
    instagramService.follow(userId2, userId1);

    String postId1 = instagramService.addPost(userId1, "some content1", "some image url");
    String postId2 = instagramService.addPost(userId1, "some content2", "some image url");

    String postId3 = instagramService.addPost(userId2, "some content3", "some image url");
    String postId4 = instagramService.addPost(userId2, "some content4", "some image url");

    String commentId1 = instagramService.addComment(userId2, postId1, "good post1", ContentType.POST);
    String commentId2 = instagramService.addComment(userId2, postId2, "very nice1", ContentType.POST);

    String commentId3 = instagramService.addComment(userId1, postId3, "good post2", ContentType.POST);
    String commentId4 = instagramService.addComment(userId1, postId4, "very nice2", ContentType.POST);

    String commentId5 = instagramService.addComment(userId1, commentId3, "not good2", ContentType.COMMENT);
    String commentId6 = instagramService.addComment(userId1, commentId4, "not very nice2", ContentType.COMMENT);

    instagramService.like(userId1, postId3, ContentType.POST);
    instagramService.like(userId2, postId1, ContentType.POST);

    instagramService.like(userId1, commentId3, ContentType.COMMENT);
    instagramService.like(userId2, commentId1, ContentType.COMMENT);

    List<Post> feed1 = instagramService.getFeed(userId1, 0,10);
    List<Post> feed2 = instagramService.getFeed(userId2, 0,10);

    FeedPrinterUtil.printFeed(feed1, userId1);
    FeedPrinterUtil.printFeed(feed2, userId2);
  }
}


