package instagramlld.utiil;

import instagramlld.dao.PostRepoImpl;
import instagramlld.dao.UserRepoImpl;
import instagramlld.model.Comment;
import instagramlld.model.Post;
import java.util.List;

public class FeedPrinterUtil {
  public static void printFeed(List<Post> feed, String userId) {
    System.out.println("printing feed for user : " + UserRepoImpl.getInstance().getUserFromId(userId).getName());
    for(Post post:feed) {
      System.out.println(printPost(post));
    }
  }

  private static final String DELIM = "\t";

  private static String printPost(Post post) {
    return "POST : \n" + post.getImageUrl() + "\n" + post.getContent() + "\n posted by : " +
        UserRepoImpl.getInstance().getUserFromId(post.getUserId()).getName() + "\n likes : " +
        PostRepoImpl.getInstance().getLikesCount(post.getId()) + "\n" +
        printComment(post.getId(), DELIM);
  }

  private static String printComment(String parentId, String spacing) {
    List<Comment> comments = PostRepoImpl.getInstance().getComments(parentId);
    StringBuilder res = new StringBuilder();
    for(Comment comment:comments) {
      res.append(spacing).append(comment.getContent())
          .append(printComment(comment.getId(), spacing + DELIM)).append("\n");
    }
    return res.toString();
  }
}
