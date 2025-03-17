package SearchEngine;

import SearchEngine.enums.CategoryType;
import SearchEngine.models.Blog;
import SearchEngine.models.Content;
import SearchEngine.models.Dataset;
import SearchEngine.models.Title;
import SearchEngine.models.User;
import SearchEngine.services.SearchEngineService;
import SearchEngine.services.UserService;
import SearchEngine.services.order.CreatedOrderStrategy;
import SearchEngine.services.search.NormalSearchStrategy;
import java.util.List;

public class SearchEngineDriver {

  public static void main(String[] args) {
    SearchEngineService searchEngineService = new SearchEngineService();
    UserService userService = new UserService();

    User abhiram = userService.createUser("abhiram");
    User shaji = userService.createUser("shaji");

    Dataset techSet = searchEngineService.createDataset(CategoryType.TECH);
    Dataset jobSet = searchEngineService.createDataset(CategoryType.JOBS);

    searchEngineService.createBlog(new Title("title1"), new Content("how java works"), abhiram, techSet);
    searchEngineService.createBlog(new Title("title2"), new Content("how java collection works"), shaji, techSet);
    searchEngineService.createBlog(new Title("title3"), new Content("how java works in java 8"), abhiram, techSet);

    searchEngineService.createBlog(new Title("title4"), new Content("find java developer jobs"), abhiram, jobSet);

    List<Blog> blogList1 = searchEngineService.searchBlog(techSet, "java", new NormalSearchStrategy(), new CreatedOrderStrategy());
    System.out.println("printing blogList1");
    for(Blog blog:blogList1) {
      System.out.println(blog.toString());
    }

    List<Blog> blogList2 = searchEngineService.searchBlog(jobSet, "java", new NormalSearchStrategy(), new CreatedOrderStrategy());

    System.out.println("printing blogList2");
    for(Blog blog:blogList2) {
      System.out.println(blog.toString());
    }
  }
}
