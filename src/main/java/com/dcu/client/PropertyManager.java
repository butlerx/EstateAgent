package com.dcu.client;

import com.dcu.Bid;
import com.dcu.Booking;
import com.dcu.Property;
import java.time.LocalDateTime;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.jackson.JacksonFeature;

class PropertyManager {
  private static final String REST_URI = "http://localhost:8080/api/property";
  private static final Client client = ClientBuilder.newBuilder()
    .register(JacksonFeature.class)
    .build();

  public void createProperty(Property prop) {
    client
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

  public void placeBid(int id, Bid bid) {
    client
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

  public void makeBooking(int id, Booking booking) {
    client
      .target(REST_URI)
      .path(String.valueOf(id))
      .path("booking")
      .request(MediaType.APPLICATION_JSON)
      .post(Entity.entity(booking, MediaType.APPLICATION_JSON));
  }
}
