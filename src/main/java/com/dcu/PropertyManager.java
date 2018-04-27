package com.dcu;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.springframework.beans.factory.annotation.Autowired;

@Path("property")
public class PropertyManager {

  private static final Logger LOGGER = Logger.getLogger(PropertyManager.class.getName());
  @Autowired private PropertyDB properties;

  public PropertyManager() {
    LOGGER.fine("PropertyManager()");
  }

  @GET
  @Produces(value = {APPLICATION_JSON})
  public List<Property> getAll(
      @DefaultValue("0") @QueryParam("min") final int min,
      @DefaultValue("2147483647") @QueryParam("max") final int max) {
    LOGGER.fine(String.format("Get All(min: %d, max: %d)", min, max));
    return properties
        .getAll()
        .stream()
        .filter(p -> p.getPrice() > min && p.getPrice() < max)
        .collect(Collectors.toList());
  }

  @POST
  @Consumes(value = {APPLICATION_JSON})
  public Response add(final Property prop, @Context final UriInfo uriInfo) {
    int id = properties.add(prop);
    return Response.status(Response.Status.CREATED.getStatusCode())
        .header("Location", String.format("%s/%s", uriInfo.getAbsolutePath().toString(), id))
        .build();
  }

  @GET
  @Path("/{id}")
  @Produces(value = {APPLICATION_JSON})
  public Property get(@PathParam("id") final int id) {
    return properties.get(id);
  }

  @PUT
  @Path("/{id}")
  @Consumes(value = {APPLICATION_JSON})
  public Response update(final Property prop, @PathParam("id") final int id) {
    properties.update(prop, id);
    return Response.status(Response.Status.OK.getStatusCode()).build();
  }

  @DELETE
  @Path("/{id}")
  @Consumes(value = {APPLICATION_JSON})
  public Response delete(final Property prop, @PathParam("id") final int id) {
    properties.delete(id);
    return Response.status(Response.Status.OK.getStatusCode()).build();
  }

  @GET
  @Path("/{id}/bid")
  @Produces(value = {APPLICATION_JSON})
  public Bid getBid(@PathParam("id") final int id) {
    return properties.get(id).getBid();
  }

  @POST
  @Path("/{id}/bid")
  @Produces({APPLICATION_JSON})
  public Response bid(@PathParam("id") final int id, final Bid bid) {
    System.out.println(bid.offer + " " + bid.bidder);
    properties.get(id).bid(bid.offer, bid.bidder);
    return Response.status(Response.Status.CREATED.getStatusCode())
        .entity(String.format("Bid of %d on property %d accepted", bid.offer, id))
        .build();
  }

  @GET
  @Path("/{id}/booking")
  @Produces(value = {APPLICATION_JSON})
  public LocalDateTime[] getBooking(@PathParam("id") final int id) {
    return properties.get(id).getFreeViewings();
  }

  @POST
  @Path("/{id}/booking")
  @Produces(value = {APPLICATION_JSON})
  public Response book(final Booking booking, @PathParam("id") final int id) {
    properties.get(id).book(booking.time, booking.getBooker());
    return Response.status(Response.Status.CREATED.getStatusCode())
        .entity(String.format("Booking for %s on property %d accepted", booking.time, id))
        .build();
  }
}
