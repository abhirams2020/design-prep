package instagramlld.model;

import java.util.UUID;
import lombok.Getter;

@Getter
public class Like {
  private final String id;
  private final String userId;
  private final String contentId;

  public Like(String contentId, String userId) {
    this.id = UUID.randomUUID().toString();
    this.userId = userId;
    this.contentId = contentId;
  }
}
