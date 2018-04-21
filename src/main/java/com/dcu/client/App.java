package com.dcu.client;

import com.dcu.AlreadyBooked;
import com.dcu.Bid;
import com.dcu.BidTooLow;
import com.dcu.Booking;
import com.dcu.Property;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

class PropertyManager {
  private static final String REST_URI = "http://localhost:8080/api/property";
  private final Client client = ClientBuilder.newClient();

  public Response createProperty(Property prop) {
    return client
        .target(REST_URI)
        .request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(prop, MediaType.APPLICATION_JSON));
  }

  public Property getProperty(int id) {
    return client
        .target(REST_URI)
        .path(String.valueOf(id))
        .request(MediaType.APPLICATION_JSON)
        .get(Property.class);
  }

  public List<Property> getAllProperty(final int min, final int max) {
    return client
        .target(REST_URI)
        .queryParam("min", min)
        .queryParam("max", max)
        .request(MediaType.APPLICATION_JSON)
        .get(new GenericType<List<Property>>() {});
  }

  public Bid getBids(int id) {
    return client
        .target(REST_URI)
        .path(String.valueOf(id))
        .path("bid")
        .request(MediaType.APPLICATION_JSON)
        .get(Bid.class);
  }

  public Response placeBid(int id, Bid bid) {
    return client
        .target(REST_URI)
        .path(String.valueOf(id))
        .path("bid")
        .request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(bid, MediaType.APPLICATION_JSON));
  }

  public List<LocalDateTime> getBooking(int id) {
    return client
        .target(REST_URI)
        .path(String.valueOf(id))
        .path("booking")
        .request(MediaType.APPLICATION_JSON)
        .get(new GenericType<List<LocalDateTime>>() {});
  }

  public Response makeBooking(int id, Booking booking) {
    return client
        .target(REST_URI)
        .path(String.valueOf(id))
        .path("booking")
        .request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(booking, MediaType.APPLICATION_JSON));
  }
}

class App {
  public static void main(String[] args) {
    final PropertyManager props = new PropertyManager();
    final Scanner scanner = new Scanner(System.in);
    System.out.print("Enter your name: ");
    String name = scanner.nextLine();
    while (true) {
      System.out.println(
          String.join(
              "\n",
              "Choose a Command: ",
              "(1) List all Property",
              "(2) Get property by id",
              "(3) get bids for property",
              "(4) Bid on property",
              "(5) Get Bookings for property",
              "(6) Book viewing for property",
              "(7) Create Property",
              "(0) quit"));
      final int input = scanner.nextInt();
      switch (input) {
        case 1:
          System.out.println("Do You want to filter by price (y/N)");
          final String filter = scanner.nextLine();
          int min = 0;
          int max = 2147483647;
          if ("y".equals(filter) && "Y".equals(filter)) {
            System.out.print("What is your minimum price");
            min = scanner.nextInt();
            System.out.print("What is your maximum price");
            max = scanner.nextInt();
          }
          System.out.println(props.getAllProperty(min, max));
          break;
        case 2:
          System.out.print("Whats the id of the property: ");
          System.out.println(props.getProperty(scanner.nextInt()));
          break;
        case 3:
          System.out.print("Whats the id of the property: ");
          System.out.println(props.getBids(scanner.nextInt()));
          break;
        case 4:
          System.out.print("Whats the id of the property: ");
          int propID = scanner.nextInt();
          boolean offerMade = false;
          while (!offerMade) {
            final Property prop = props.getProperty(propID);
            System.out.println(
                "The Property has an asking price of "
                    + prop.getPrice()
                    + "\nThe Higgest bid is "
                    + prop.getBid().getOffer());
            int offer = scanner.nextInt();
            while (offer <= prop.getBid().getOffer()) {
              System.out.print("Offer not high enough: ");
              offer = scanner.nextInt();
            }
            try {
              props.placeBid(propID, new Bid(name, offer));
              offerMade = true;
            } catch (BidTooLow e) {
              System.out.print("Offer not high enough: ");
            }
          }
          System.out.println("Offer made");
          break;
        case 5:
          System.out.print("Whats the id of the property: ");
          System.out.println(props.getBooking(scanner.nextInt()));
          break;
        case 6:
          System.out.print("Whats the id of the property: ");
          propID = scanner.nextInt();
          boolean bookingMade = false;
          // This actually results in a lot of api calls but java lamda needs the use of finals
          while (!bookingMade) {
            List<LocalDateTime> freeViewings = props.getBooking(propID);
            System.out.println("Avalible Viewing :\n" + freeViewings);
            final LocalDateTime slot =
                LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            if (!freeViewings.stream().anyMatch(viewing -> viewing == slot)) {
              System.out.print("Booking not availible");
            } else {
              try {
                Booking booking = new Booking(slot);
                booking.book(name);
                props.makeBooking(propID, booking);
                bookingMade = true;
              } catch (AlreadyBooked e) {
                System.out.print("Booking not availible");
              }
            }
          }
          System.out.println("Booking made");
          break;
        case 7:
          System.out.print(
              String.join("\n", "Choose a property type: ", "(1) house", "(2) apartment"));
          final int typeArg = scanner.nextInt();
          String type = "";
          if (typeArg == 1) {
            type = "house";
          } else if (typeArg == 2) {
            type = "apartment";
          } else {
            System.out.println("Dont recognise type");
            break;
          }
          System.out.print("What is the postcode: ");
          final int postcode = scanner.nextInt();
          System.out.print("How many bedroos does it have: ");
          final int bedrooms = scanner.nextInt();
          System.out.print("What is the asking price: ");
          final int price = scanner.nextInt();
          System.out.print("How many days to offer for: ");
          final int days = scanner.nextInt();
          props.createProperty(new Property(type, postcode, bedrooms, price, days, 0));
          break;
        case 0:
          System.exit(0);
      }
    }
  }
}
