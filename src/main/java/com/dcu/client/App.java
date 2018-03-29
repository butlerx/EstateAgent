package com.dcu.client;

import com.dcu.Property;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
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
}
