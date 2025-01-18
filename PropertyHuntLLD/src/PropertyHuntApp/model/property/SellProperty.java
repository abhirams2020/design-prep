package PropertyHuntApp.model.property;

import PropertyHuntApp.enums.ListingType;
import PropertyHuntApp.enums.PropertyStatus;
import PropertyHuntApp.enums.RoomType;
import PropertyHuntApp.model.size.Size;
import PropertyHuntApp.model.user.User;
import java.util.UUID;

public class SellProperty implements Property {

  String id;
  String name;
  String location;
  Integer price;
  Size size;
  RoomType roomType;
  PropertyStatus propertyStatus;
  ListingType listingType;
  User seller;

  public SellProperty(User seller, String name, String location, Integer price, Size size,
      RoomType roomType) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    this.location = location;
    this.price = price;
    this.size = size;
    this.roomType = roomType;
    this.propertyStatus = PropertyStatus.AVAILABLE;
    this.listingType = ListingType.SELL;
    this.seller = seller;
  }


  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getLocation() {
    return location;
  }

  public Integer getPrice() {
    return price;
  }

  public Size getSize() {
    return size;
  }

  public RoomType getRoomType() {
    return roomType;
  }

  public void setPropertyStatus(PropertyStatus propertyStatus) {
    this.propertyStatus = propertyStatus;
  }

  public User getSeller() {
    return seller;
  }

  @Override
  public void setTitle(String value) {
    this.name = value;
  }

  @Override
  public void setLocation(String value) {
    this.location = value;
  }

  @Override
  public void setPrice(Integer value) {
    this.price = value;
  }

  @Override
  public void setSize(Size size) {
    this.size = size;
  }

  @Override
  public void setRoomType(RoomType roomType) {
    this.roomType = roomType;
  }

  public PropertyStatus getPropertyStatus() {
    return propertyStatus;
  }

  public ListingType getListingType() {
    return listingType;
  }
}
