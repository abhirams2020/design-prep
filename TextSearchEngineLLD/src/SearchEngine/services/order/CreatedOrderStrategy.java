package SearchEngine.services.order;

import SearchEngine.models.Blog;
import java.util.ArrayList;
import java.util.List;

public class CreatedOrderStrategy implements OrderStrategy {

  @Override
  public List<Blog> order(List<Blog> blogList) {
    List<Blog> newList = new ArrayList<>(blogList);
    newList.sort((a,b)-> b.getCreatedAt().compareToIgnoreCase(a.getCreatedAt()));
    return newList;
  }
}
