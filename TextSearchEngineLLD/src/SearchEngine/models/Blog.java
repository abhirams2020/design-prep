package SearchEngine.models;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class Blog {
  Title title;
  Content content;
  User author;
  Dataset dataset;
  String createdAt;
  String updatedAt;

  public Blog(Title title, Content content, User author, Dataset dataset) {
    this.title = title;
    this.content = content;
    this.author = author;
    this.dataset = dataset;
    this.createdAt = LocalDateTime.now().toString();
    this.updatedAt = this.createdAt;
  }

  private void updateTime() {
    updatedAt = LocalDateTime.now().toString();
  }

  public Blog setTitle(Title title) {
    updateTime();
    this.title = title;
    return this;
  }

  public Blog setContent(Content content) {
    updateTime();
    this.content = content;
    return this;
  }

  public Blog setAuthor(User author) {
    updateTime();
    this.author = author;
    return this;
  }

  @Override
  public String toString() {
    return title.getTitle() + " -> " + content.getContent() + ", by " + author.getName() + ", updatedAt : " + updatedAt + "\n";
  }
}
