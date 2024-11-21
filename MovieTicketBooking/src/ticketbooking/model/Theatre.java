package ticketbooking.model;

import java.util.List;

public class Theatre {
  public String id;
  public String name;
  public String location;
  public List<Screen> screenList;

  public Theatre(String id, String name, String location, List<Screen> screenList) {
    this.id = id;
    this.name = name;
    this.location = location;
    this.screenList = screenList;
  }
}
