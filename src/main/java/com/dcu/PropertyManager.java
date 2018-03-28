package com.dcu;

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
import javax.ws.rs.core.MediaType;
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
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public List<Property> getAll(
      @DefaultValue("0") @QueryParam("min") int min,
      @DefaultValue("2147483647") @QueryParam("max") int max) {
    LOGGER.fine("Get All(min: " + min + ", max: " + max + ")");
    return properties
        .getAll()
        .stream()
        .filter(p -> p.getPrice() > min)
        .filter(p -> p.getPrice() < max)
        .collect(Collectors.toList());
  }

  @GET
  @Path("/{id}")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Property get(@PathParam("id") int id) {
    return properties.get(id);
  }

  @POST
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Response add(Property prop, @Context UriInfo uriInfo) {
    int id = properties.add(prop);
    return Response.status(Response.Status.CREATED.getStatusCode())
        .header("Location", String.format("%s/%s", uriInfo.getAbsolutePath().toString(), id))
        .build();
  }

  @PUT
  @Path("/{id}")
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Response update(Property prop, @PathParam("id") int id) {
    properties.update(prop, id);
    return Response.status(Response.Status.OK.getStatusCode()).build();
  }

  @DELETE
  @Path("/{id}")
  @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Response delete(Property prop, @PathParam("id") int id) {
    properties.delete(id);
    return Response.status(Response.Status.OK.getStatusCode()).build();
  }

  @POST
  @Path("/{id}/bid")
  @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
  public Response bid(Bid bid, @PathParam("id") int id, @Context UriInfo uriInfo) {
    Property prop = properties.get(id);
    return Response.status(Response.Status.CREATED.getStatusCode())
        .entity(prop.bid(bid.getOffer(), bid.getBidder()))
        .build();
  }
}
