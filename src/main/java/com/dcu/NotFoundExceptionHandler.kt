package com.dcu

import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class NotFoundExceptionHandler : ExceptionMapper<NotFound> {
    override fun toResponse(ex: NotFound): Response {
        return Response.status(Response.Status.NOT_FOUND).build()
    }
}
