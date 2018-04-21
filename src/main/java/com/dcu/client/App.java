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

  // TODO add args for min and max
  public List<Property> getAllProperty() {
    return client
        .target(REST_URI)
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
      int input = scanner.nextInt();
      switch (input) {
        case 1:
          // TODO Add prompt for min and max price
          System.out.println(props.getAllProperty());
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
          if (typeArg == 1) {
            final String type = "house";
          } else if (typeArg == 2) {
            final String type = "apartment";
          } else {
            System.out.println("Dont recognise type");
            break;
          }
          // TODO
          // get postcode
          // get bedrooms
          // get price
          // get days to offer for
          // get next available id, get all propertys and use length() // hack i know but meh
          // push to server
          break;
        case 0:
          System.exit(0);
      }
    }
  }
}
