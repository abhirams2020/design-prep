package instagramlld.model;

import instagramlld.enums.ContentType;
import instagramlld.enums.PostStatus;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Post{
  @Getter
  String id, userId, content, imageUrl;

  @Setter
  int commentCount, likeCount;

  @Setter
  PostStatus postStatus;

  public Post(String userId, String content, String imageUrl) {
    this.id = UUID.randomUUID().toString();
    this.userId = userId;
    this.content = content;
    this.imageUrl = imageUrl;
    this.likeCount = 0;
    this.commentCount = 0;
    this.postStatus = PostStatus.ACTIVE;
  }
}
