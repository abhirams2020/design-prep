package SearchEngine.services.order;

import SearchEngine.models.Blog;
import java.util.List;

public interface OrderStrategy {
  List<Blog> order(List<Blog> blogList);
}
