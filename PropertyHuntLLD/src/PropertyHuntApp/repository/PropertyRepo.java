package PropertyHuntApp.repository;

import PropertyHuntApp.enums.ListingType;
import PropertyHuntApp.enums.PropertyStatus;
import PropertyHuntApp.enums.RoomType;
import PropertyHuntApp.model.property.Property;
import PropertyHuntApp.model.user.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PropertyRepo {

  private final Map<User, Set<Property>> propertyMap = new HashMap<>();
  private final Set<Property> availablePropertySet = new HashSet<>();
  private final Map<User, Set<Property>> userShortlistMap = new HashMap<>();

  public void addProperty(Property property, User user) {
    if (!propertyMap.containsKey(user)) {
      propertyMap.put(user, new HashSet<>());
    }
    propertyMap.get(user).add(property);
    availablePropertySet.add(property);
  }

  public void removeProperty(Property property, User user) {
    if (!propertyMap.containsKey(user)) {
      propertyMap.put(user, new HashSet<>());
    }
    propertyMap.get(user).remove(property);
    availablePropertySet.remove(property);
  }

  public List<Property> getPropertyByUser(User user) {
    if (!propertyMap.containsKey(user)) {
      return List.of();
    }
    List<Property> res = new ArrayList<>();
    res.addAll(propertyMap.get(user));
    return res;
  }

  public List<Property> searchPropertyByParam(String location, Integer minPrice, Integer maxPrice,
      List<RoomType> roomTypeList, Integer minSize, Integer maxSize, ListingType listingType) {
    List<Property> res = new ArrayList<>();
    for (Property p : availablePropertySet) {
      if (location == p.getLocation()
          && minPrice <= p.getPrice()
          && maxPrice >= p.getPrice()
          && roomTypeList.contains(p.getRoomType())
          && minSize <= p.getSize().getSize()
          && maxSize >= p.getSize().getSize()
          && listingType == p.getListingType()) {
        res.add(p);
      }
    }
    return res;
  }

  public void markAsSold(Property property) {
    property.setPropertyStatus(PropertyStatus.SOLD);
    availablePropertySet.remove(property);
  }

  public void markShortlisted(Property property, User user) {
    if (!userShortlistMap.containsKey(user)) {
      userShortlistMap.put(user, new HashSet<>());
    }
    userShortlistMap.get(user).add(property);
  }

  public void removeShortlisted(Property property, User user) {
    if (!userShortlistMap.containsKey(user)) {
      return;
    }
    userShortlistMap.get(user).remove(property);
  }

  public List<Property> getShortlisted(User user) {
    if (!userShortlistMap.containsKey(user)) {
      return List.of();
    }
    List<Property> res = new ArrayList<>();
    res.addAll(userShortlistMap.get(user));
    return res;
  }

  public Property getPropertyById(String propertyId) {
    for (User user : propertyMap.keySet()) {
      for (Property property : propertyMap.get(user)) {
        if (property.getId().equals(propertyId)) {
          return property;
        }
      }
    }
    return null;
  }
}
