package SearchEngine.services.search;

import SearchEngine.models.Blog;
import SearchEngine.models.Dataset;
import java.util.List;

public interface SearchStrategy {
  List<Blog> search(Dataset dataset, String pattern);
}
