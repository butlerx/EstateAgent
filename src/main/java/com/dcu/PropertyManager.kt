package com.dcu

import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime
import java.util.logging.Logger
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo
import kotlin.streams.toList

@Path("property")
class PropertyManager {
    @Autowired
    private val properties: PropertyDB? = null

    init {
        LOGGER.fine("PropertyManager()")
    }

    @GET
    @Produces(value = [APPLICATION_JSON])
    fun getAll(
      @DefaultValue("0") @QueryParam("min") min: Int,
      @DefaultValue("2147483647") @QueryParam("max") max: Int
    ): List<Property> {
        LOGGER.fine(String.format("Get All(min: %d, max: %d)", min, max))
        return properties!!
                .all
                .stream()
                .filter { p -> p.price in (min + 1)..(max - 1) }
                .toList()
    }

    @POST
    @Consumes(value = [APPLICATION_JSON])
    fun add(prop: Property, @Context uriInfo: UriInfo): Response {
        val id = properties!!.add(prop)
        return Response.status(Response.Status.CREATED.statusCode)
                .header("Location", String.format("%s/%s", uriInfo.absolutePath.toString(), id))
                .build()
    }

    @GET
    @Path("/{id}")
    @Produces(value = [APPLICATION_JSON])
    operator fun get(@PathParam("id") id: Int): Property {
        return properties!![id]
    }

    @PUT
    @Path("/{id}")
    @Consumes(value = [APPLICATION_JSON])
    fun update(prop: Property, @PathParam("id") id: Int): Response {
        properties!!.update(prop, id)
        return Response.status(Response.Status.OK.statusCode).build()
    }

    @DELETE
    @Path("/{id}")
    @Consumes(value = [APPLICATION_JSON])
    fun delete(prop: Property, @PathParam("id") id: Int): Response {
        properties!!.delete(id)
        return Response.status(Response.Status.OK.statusCode).build()
    }

    @GET
    @Path("/{id}/bid")
    @Produces(value = [APPLICATION_JSON])
    fun getBid(@PathParam("id") id: Int): Bid {
        return properties!![id].bid
    }

    @POST
    @Path("/{id}/bid")
    @Produces(APPLICATION_JSON)
    fun bid(@PathParam("id") id: Int, bid: Bid): Response {
        println(bid.offer.toString() + " " + bid.bidder)
        properties!![id].bid(bid.offer, bid.bidder)
        return Response.status(Response.Status.CREATED.statusCode)
                .entity(String.format("Bid of %d on property %d accepted", bid.offer, id))
                .build()
    }

    @GET
    @Path("/{id}/booking")
    @Produces(value = [APPLICATION_JSON])
    fun getBooking(@PathParam("id") id: Int): Array<LocalDateTime> {
        return properties!![id].freeViewings
    }

    @POST
    @Path("/{id}/booking")
    @Produces(value = [APPLICATION_JSON])
    fun book(booking: Booking, @PathParam("id") id: Int): Response {
        properties!![id].book(booking.time, booking.booker)
        return Response.status(Response.Status.CREATED.statusCode)
                .entity(String.format("Booking for %s on property %d accepted", booking.time, id))
                .build()
    }

    companion object {
        private val LOGGER = Logger.getLogger(PropertyManager::class.java.name)
    }
}