package com.dcu

import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class BidTooLowExceptionHandler : ExceptionMapper<NotFound> {
    override fun toResponse(ex: NotFound): Response {
        return Response.status(Response.Status.CONFLICT).entity("Bid too Low to be accepted").build()
    }
}
