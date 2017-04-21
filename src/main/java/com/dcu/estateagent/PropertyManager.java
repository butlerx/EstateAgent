package com.dcu.estateagent;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.util.*;

@Path("/api")
public class PropertyManager {

  private ArrayList<Property> properties = new ArrayList<Property>();

  /*properties.add(new Property(1, "house", 3, 2, 375000));
  properties.add(new Property(2, "house", 5, 3, 360000));
  properties.add(new Property(3, "house", 3, 3, 500000));
  properties.add(new Property(4, "apartment", 5, 2, 250000));
  properties.add(new Property(5, "apartment", 7, 1, 150000));*/

  @GET
  @Path("/list")
  @Produces(MediaType.APPLICATION_JSON)
  public ArrayList<Property> getMsg() {
    return properties;
  }

  @GET
  @Path("/list/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getProperty(@PathParam("id") int id) {
    final Property prop = properties.get(id);
    if (prop == "null") {
      return Response.status(Response.Status.NOT_FOUND).entity("ID not found: " + id).build();
    }
    return Response.ok(prop);
  }

  @PUT
  @Path("/add")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response consumeJSON(Property prop) {
    String output = prop.toString();
    properties.add(prop);
    return Response.status(200).entity(output).build();
  }
}

class Property {
  private int id;
  private String type;
  private int district;
  private int bedrooms;
  private int price;

  public Property(int id, String type, int dist, int bed, int price) {
    this.id = id;
    this.type = type;
    this.district = dist;
    this.bedrooms = bed;
    this.price = price;
  }

  public int getID() {
    return id;
  }

  public String getType() {
    return type;
  }

  public int getDistrict() {
    return district;
  }

  public int getBedrooms() {
    return bedrooms;
  }

  public int getPrice() {
    return price;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setDistrict(int dist) {
    this.district = dist;
  }

  public void setBedrooms(int bed) {
    this.bedrooms = bed;
  }

  public void setPrice(int price) {
    this.price = price;
  }
}
