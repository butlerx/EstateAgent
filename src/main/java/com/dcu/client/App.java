package com.dcu.client;

import com.dcu.Bid;
import com.dcu.Property;
import java.time.LocalDateTime;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

class App {

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

  public List<LocalDateTime> getBooking(int id) {
    return client
        .target(REST_URI)
        .path(String.valueOf(id))
        .path("booking")
        .request(MediaType.APPLICATION_JSON)
        .get(new GenericType<List<LocalDateTime>>() {});
  }

}
