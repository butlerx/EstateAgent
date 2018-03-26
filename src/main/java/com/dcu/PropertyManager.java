package com.dcu;

import java.util.ArrayList;
import java.util.Random;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("property")
public class PropertyManager {

  private ArrayList<Property> properties = new ArrayList<Property>();
  private String[] types = {"apartment", "house"};

  public void seed() {
    Random rand = new Random();

    for (int i = 0; i < 100; i++) {
      this.properties.add(
          new Property(
              types[rand.nextInt(types.length - 1)],
              rand.nextInt(24) + 1,
              rand.nextInt(4) + 1,
              100000 + (int) (rand.nextDouble() * ((1000000 - 100000) + 1))));
    }
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String listAll() {
    String res = "[";
    for (Property p : properties) {
      res += "{" + p + "},";
    }
    return res.substring(0, res.length() - 1) + "]";
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getProperty(@PathParam("id") int id) {
    if (properties.size() < id) {
      return Response.status(Response.Status.NOT_FOUND).entity("ID not found: " + id).build();
    }
    return Response.status(200).entity(properties.get(id)).build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response consumeJSON(Property prop) {
    properties.add(prop);
    return Response.status(200).entity("Property Added with id: " + properties.size()).build();
  }
}
