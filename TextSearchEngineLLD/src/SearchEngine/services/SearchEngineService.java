package SearchEngine.services;

import SearchEngine.enums.CategoryType;
import SearchEngine.models.Blog;
import SearchEngine.models.Content;
import SearchEngine.models.Dataset;
import SearchEngine.models.Title;
import SearchEngine.models.User;
import SearchEngine.services.order.OrderStrategy;
import SearchEngine.services.search.SearchStrategy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchEngineService {
  Map<CategoryType, Dataset> datasetMap = new HashMap<>();

  public Dataset createDataset(CategoryType categoryType) {
    Dataset dataset = new Dataset(categoryType);
    datasetMap.put(categoryType, dataset);
    return dataset;
  }

  public Blog createBlog(Title title, Content content, User author, Dataset dataset) {
    Blog blog = new Blog(title, content, author, dataset);
    dataset.addBlog(title.getTitle(), blog);
    return blog;
  }

  public void deleteBlog(Dataset dataset, String id) {
    dataset.removeBlog(id);
  }

  public List<Blog> searchBlog(Dataset dataset, String pattern, SearchStrategy searchStrategy, OrderStrategy orderStrategy) {
    return orderStrategy.order(searchStrategy.search(dataset, pattern));
  }

  public Blog findBlog(Dataset dataset, String id) {
    return dataset.getBlogById(id);
  }
}
