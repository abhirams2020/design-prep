package PropertyHuntApp.service.factory;

import PropertyHuntApp.enums.ListingType;
import PropertyHuntApp.enums.RoomType;
import PropertyHuntApp.model.property.Property;
import PropertyHuntApp.model.property.RentProperty;
import PropertyHuntApp.model.property.SellProperty;
import PropertyHuntApp.model.size.Size;
import PropertyHuntApp.model.user.User;

public class PropertyFactory {
  public static Property getProperty(User user, String propertyName, String location, Integer price,
      ListingType listingType, Size size, RoomType roomType) {
    Property property;
    if (listingType == ListingType.RENT) {
      property = new RentProperty(user, propertyName, location, price, size, roomType);
    } else if (listingType == ListingType.SELL) {
      property = new SellProperty(user, propertyName, location, price, size, roomType);
    } else {
      throw new IllegalArgumentException("invalid listing type given");
    }
    return property;
  }
}
