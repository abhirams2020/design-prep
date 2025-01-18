package PropertyHuntApp.service;

import PropertyHuntApp.enums.ListingType;
import PropertyHuntApp.enums.RoomType;
import PropertyHuntApp.enums.UserType;
import PropertyHuntApp.service.factory.PropertyFactory;
import PropertyHuntApp.service.factory.UserFactory;
import PropertyHuntApp.model.property.Property;
import PropertyHuntApp.model.size.MetricSize;
import PropertyHuntApp.model.user.User;
import PropertyHuntApp.repository.PropertyRepo;
import PropertyHuntApp.repository.UserRepo;
import java.util.Comparator;
import java.util.List;

public class PropertyHuntService {

  public static volatile PropertyHuntService instance = null;
  private final UserRepo userRepo;
  private final PropertyRepo propertyRepo;
  User user;

  private PropertyHuntService() {
    userRepo = new UserRepo();
    propertyRepo = new PropertyRepo();
  }

  public static PropertyHuntService getInstance() {
    if (instance == null) {
      synchronized (PropertyHuntService.class) {
        if (instance == null) {
          instance = new PropertyHuntService();
        }
      }
    }
    return instance;
  }

  public void register(String username) {
    register(username, UserType.DEFAULT);
  }

  public void register(String username, UserType userType) {
    User user = UserFactory.getUser(username, userType);
    userRepo.register(user);
  }

  public void login(String username) {
    if(user!=null && !user.getUsername().equalsIgnoreCase(username)) {
      throw new RuntimeException("another user logged in");
    }
    this.user = userRepo.login(username);
  }

  public void logout(String username) {
    userRepo.logout(user);
    user = null;
  }

  public void listProperty(String propertyName, String location, Integer price,
      ListingType listingType, Integer size, RoomType roomType) {
    validateUser();
    Property property = PropertyFactory.getProperty(user, propertyName, location, price,
        listingType, new MetricSize(size), roomType);
    propertyRepo.addProperty(property, user);
  }

  public List<Property> search(String location, Integer minPrice, Integer maxPrice,
      ListingType listingType, Integer size, Integer numRooms, String sortType) {
    List<RoomType> roomTypeList;
    if (numRooms == 1) {
      roomTypeList = List.of(RoomType.RK1, RoomType.BHK1);
    } else if (numRooms == 2) {
      roomTypeList = List.of(RoomType.BHK2);
    } else if (numRooms == 3) {
      roomTypeList = List.of(RoomType.BHK3);
    } else {
      throw new IllegalArgumentException("invalid number of rooms input");
    }
    List<Property> res = propertyRepo.searchPropertyByParam(location, minPrice, maxPrice,
        roomTypeList, size,
        size, listingType);
    if (sortType.equalsIgnoreCase("AREA")) {
      res.sort(Comparator.comparingInt(a -> a.getSize().getSize()));
    } else if (sortType.equalsIgnoreCase("PRICE")) {
      res.sort(Comparator.comparing(Property::getPrice));
    } else {
      throw new IllegalArgumentException("invalid sort type input");
    }
    return res;
  }

  public void shortList(String propertyId) {
    validateUser();
    Property property = propertyRepo.getPropertyById(propertyId);
    if (property == null) {
      throw new IllegalArgumentException("property not available");
    }
    propertyRepo.markShortlisted(property, user);
  }

  public List<Property> viewShortlisted() {
    return propertyRepo.getShortlisted(user);
  }

  public List<Property> viewListed() {
    return propertyRepo.getPropertyByUser(user);
  }

  public void markSold(Property property) {
    validateUser();
    if(user != property.getSeller()) {
      throw new IllegalArgumentException("invalid user trying to mark sold");
    }
    propertyRepo.markAsSold(property);
  }

  private void validateUser() {
    if (user == null) {
      throw new RuntimeException("user not logged in");
    }
  }
}
