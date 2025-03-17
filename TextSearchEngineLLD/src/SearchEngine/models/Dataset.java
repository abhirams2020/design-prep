package SearchEngine.models;

import SearchEngine.enums.CategoryType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dataset {
  CategoryType categoryType;
  Map<String, Blog> blogMap = new HashMap<>();
  Set<Blog> blogList = new HashSet<>();

  public Dataset(CategoryType categoryType) {
    this.categoryType = categoryType;
  }

  public Blog getBlogById(String id) {
    if(blogMap.containsKey(id)) {
      return blogMap.get(id);
    }
    return null;
  }

  public List<Blog> getBlogList() {
    return new ArrayList<>(blogList);
  }

  public void addBlog(String id, Blog blog) {
    blogMap.putIfAbsent(id, blog);
    blogList.add(blog);
  }

  public void removeBlog(String id) {
    if(!blogMap.containsKey(id)) {
      return;
    }
    blogList.remove(blogMap.get(id));
    blogMap.remove(id);
  }
}
