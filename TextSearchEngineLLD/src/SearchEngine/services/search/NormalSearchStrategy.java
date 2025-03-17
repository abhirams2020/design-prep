package SearchEngine.services.search;

import SearchEngine.models.Blog;
import SearchEngine.models.Dataset;
import java.util.List;

public class NormalSearchStrategy implements SearchStrategy{

  @Override
  public List<Blog> search(Dataset dataset, String pattern) {
    return dataset.getBlogList().stream().filter(b -> b.getContent().getContent().contains(pattern)).toList();
  }
}
