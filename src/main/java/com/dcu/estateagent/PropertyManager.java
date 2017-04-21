package com.dcu.estateagent;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import org.json.*;
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
  public String getMsg() {
    return properties.toString();
  }

  @GET
  @Path("/list/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getProperty(@PathParam("id") int id) {
    if (properties.size() < id) {
      return Response.status(Response.Status.NOT_FOUND).entity("ID not found: " + id).build();
    }
    final Property prop = properties.get(id);
		return Response.status(200).entity(prop).build();
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
  private String type;
  private int district;
  private int bedrooms;
  private int price;

  public Property(String type, int dist, int bed, int price) {
    this.type = type;
    this.district = dist;
    this.bedrooms = bed;
    this.price = price;
  }

  @Override
  public String toString(){
    try {
      return new JSONObject().put("type", type).put("district", district).put("bedrooms", bedrooms).put("price", price).toString();
    } catch (JSONException e) {
      return null;
    }
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
