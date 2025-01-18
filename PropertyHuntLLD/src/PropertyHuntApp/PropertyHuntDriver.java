package PropertyHuntApp;

import PropertyHuntApp.enums.ListingType;
import PropertyHuntApp.enums.RoomType;
import PropertyHuntApp.model.property.Property;
import PropertyHuntApp.service.PropertyHuntService;
import java.util.List;

public class PropertyHuntDriver {

  public static PropertyHuntService propertyHuntService = PropertyHuntService.getInstance();

  public static void main(String[] args) {
    System.out.println("Welcome to Property Hunt!");
    try {
      // REGISTER
      propertyHuntService.register("abhirams2020@gmail.com");
      propertyHuntService.register("ab@gmail.com");
      System.out.println("Register user");
      // LOGIN SELLER
      propertyHuntService.login("abhirams2020@gmail.com");
      System.out.println("Login user");
      // LIST PROPERTY
      propertyHuntService.listProperty("3BHK for sale", "Bellandur", 1800000,
          ListingType.RENT, 250, RoomType.BHK3);
      propertyHuntService.listProperty("2BHK for sale", "Bellandur", 900000,
          ListingType.RENT, 250, RoomType.BHK2);
      System.out.println("List property");
      // VIEW LISTED
      List<Property> listed = propertyHuntService.viewListed();
      System.out.println("View listed property");
      printProperties(listed);
      // LOGOUT SELLER
//      propertyHuntService.logout("abhirams2020@gmail.com");
//      System.out.println("Logout user");

      // LOGIN BUYER
//      propertyHuntService.login("ab@gmail.com");
//      System.out.println("Login user");
      // SEARCH PROPERTY
      List<Property> properties = propertyHuntService.search("Bellandur", 1, 100000000,
          ListingType.RENT, 250, 2, "PRICE");
      System.out.println("Search property");
      printProperties(properties);
      // SHORTLIST PROPERTY
      propertyHuntService.shortList(properties.getFirst().getId());
      System.out.println("Shortlist property");
      // VIEW SHORTLISTED
      List<Property> shortListed = propertyHuntService.viewShortlisted();
      System.out.println("View shortlisted property");
      printProperties(shortListed);
      // MARK SOLD
      propertyHuntService.markSold(shortListed.getFirst());
      System.out.println("Mark sold");
      // VIEW LISTED
      List<Property> listed2 = propertyHuntService.viewListed();
      System.out.println("View listed property");
      printProperties(listed2);
      // LOGOUT BUYER
      propertyHuntService.logout("ab@gmail.com");
      System.out.println("Logout user");
    } catch (Exception e) {
      System.out.println("Error : " + e.getMessage());
    }
  }

  private static void printProperties(List<Property> properties) {
    System.out.println("Listing properties : ");
    for (Property property : properties) {
      System.out.println("Property Details:" +
          "\nProperty Name: " + property.getName() +
          "\nProperty Type: " + property.getListingType() +
          "\nLocation: " + property.getLocation() +
          "\nSize: " + property.getSize().getSize() + " " + property.getSize().getAreaUnit().getValue() +
          "\nPrice: " + property.getPrice() +
          "\nRooms: " + property.getRoomType() +
          "\nRooms: " + property.getRoomType() +
          "\nSeller: " + property.getSeller().getUsername() + "\n"
      );
    }
  }
}
