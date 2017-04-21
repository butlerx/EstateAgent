package com.dcu.estateagent;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import org.json.*;
//import org.json.parser.JSONParser;
//import org.json.parser.ParseException;
import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Path("/api")
public class PropertyManager {

  private ArrayList<Property> properties = new ArrayList<Property>();
  private LocalDate today = LocalDate.now();
  /*JSONParser parser = new JSONParser();
  JSONArray a = (JSONArray) parser.parse(new FileReader("./properties.json"));
  for (Object o : a) {
    JSONObject property = (JSONObject) o;
    properties.add(property);
  }*/

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

  @POST
  @Path("/add")
  @Consumes(MediaType.APPLICATION_JSON)
  public Response consumeJSON(Property prop) {
    String output = prop.toString();
    properties.add(prop);
    return Response.status(200).entity(output).build();
  }

  @POST
  @Path("/bid?id{id}&&bid={bid}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response bidProperty(@PathParam("id") int id, @PathParam("bid") int bid){
    if (properties.isEmpty()) {
      return Response.status(Response.Status.NOT_FOUND).entity("ID not found: " + id).build();
    }
    Property prop = properties.get(id);
    int price = prop.getPrice();
    int prevBid = prop.getBid();
    LocalDate start = prop.getStart();
    LocalDate end = prop.getEnd();
    if(bid >= prevBid && price <= bid && (start.isBefore(today)||start.equals(today))&&(end.isAfter(today)|| end.equals(today))) {
      prop.setBid(bid);
      properties.add(id, prop);
      String output = prop.toString();
      return Response.status(200).entity(output).build();
    }
    return Response.status(Response.Status.NOT_FOUND).entity("Bid Failed").build();
  }
}

class Property {
  private String type;
  private int district;
  private int bedrooms;
  private int price;
  private int highestBid = 0;
  private LocalDate startDate;
  private LocalDate endDate;

  public Property(String type, int dist, int bed, int price) {
    this.type = type;
    this.district = dist;
    this.bedrooms = bed;
    this.price = price;
    this.startDate = LocalDate.now();
    this.endDate = LocalDate.now().plusDays(5);
  }

  @Override
  public String toString(){
    try {
      return new JSONObject().put("type", type).put("district", district).put("bedrooms", bedrooms).put("price", price).put("highestBid", highestBid).put("start", startDate).put("end", endDate).toString();
    } catch (JSONException e) {
      return null;
    }
  }

  public LocalDate getStart() {
    return startDate;
  }

  public LocalDate getEnd() {
    return endDate;
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

  public void setBid(int bid) {
    this.bid = bid;
  }

  public void getBid() {
    return this.bid;
  }
}
