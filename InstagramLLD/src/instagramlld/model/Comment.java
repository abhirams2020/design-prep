package instagramlld.model;

import instagramlld.enums.CommentStatus;
import instagramlld.enums.ContentType;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Comment {
  String id, userId, content, parentId;

  @Setter
  int likeCount;

  @Setter
  CommentStatus status;

  public Comment(String userId, String content, String parentId) {
    this.id = UUID.randomUUID().toString();
    this.userId = userId;
    this.content = content;
    this.parentId = parentId;
    this.likeCount = 0;
    this.status = CommentStatus.ACTIVE;
  }
}
