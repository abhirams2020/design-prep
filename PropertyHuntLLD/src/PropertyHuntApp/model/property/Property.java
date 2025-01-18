package PropertyHuntApp.model.property;

import PropertyHuntApp.enums.ListingType;
import PropertyHuntApp.enums.PropertyStatus;
import PropertyHuntApp.enums.RoomType;
import PropertyHuntApp.model.size.Size;
import PropertyHuntApp.model.user.User;

public interface Property {

  String getId();

  String getName();

  String getLocation();

  Integer getPrice();

  Size getSize();

  RoomType getRoomType();

  void setPropertyStatus(PropertyStatus propertyStatus);

  User getSeller();

  void setTitle(String value);

  void setLocation(String value);

  void setPrice(Integer value);

  void setSize(Size size);

  void setRoomType(RoomType roomType);

  ListingType getListingType();
}
