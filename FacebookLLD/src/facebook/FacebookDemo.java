package facebook;

import facebook.model.Comment;
import facebook.model.FriendRequest;
import facebook.model.Post;
import facebook.model.User;
import facebook.service.FacebookService;

public class FacebookDemo {

  public static void main(String[] args) {
    FacebookService facebookService = FacebookService.getInstance();
    facebookService.createAccount("ab@gmail.com", "12345", "abhiram");
    facebookService.createAccount("cd@gmail.com", "12345", "joey");

    User user1 = facebookService.login("ab@gmail.com", "12345");
    User user2 = facebookService.login("cd@gmail.com", "12345");

    FriendRequest friendRequest = new FriendRequest("1", user1, user2);

    facebookService.sendFriendRequest(friendRequest);

    facebookService.acceptRequest(user2, friendRequest);

    Post post1 = facebookService.addPost(user1, "post1content", 1);
    Comment comment1 = facebookService.addComment(user2, post1, "comment1content", 2);

    facebookService.likePost(user2, post1);

    facebookService.displayFeed(user1);
  }
}
