package com.dcu;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AlreadyBookedExceptionHandler implements ExceptionMapper<NotFound> {
  public Response toResponse(NotFound ex) {
    return Response.status(Response.Status.CONFLICT).entity("Time is already booked").build();
  }
}
