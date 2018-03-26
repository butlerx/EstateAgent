package com.dcu;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("property")
public class MyResource {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Property getIt() {
    Property prop = new Property("apt", 112, 1000, 2000);
    return prop;
  }
}
