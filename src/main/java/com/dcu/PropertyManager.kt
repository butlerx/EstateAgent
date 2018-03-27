package com.dcu

import java.util.logging.Logger
import java.util.stream.Collectors
import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.DefaultValue
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo
import org.springframework.beans.factory.annotation.Autowired
import java.util.stream.Collector

@Path("property")
class PropertyManager {
    @Autowired
    val properties: PropertyDB? = null

    init {
        LOGGER.fine("PropertyManager()")
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
    fun getAll(
            @DefaultValue("0") @QueryParam("min") min: Int,
            @DefaultValue("2147483647") @QueryParam("max") max: Int): List<Property> {
        LOGGER.fine("Get All(min: $min, max: $max)")
        return properties!!
                .all
                .stream()
                .filter { p -> p.price > min }
                .filter { p -> p.price < max }
                .collect<List<Property>, Any>(Collectors.toList() as Collector<in Property, Any, List<Property>>?)
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
    operator fun get(@PathParam("id") id: Int): Property {
        return properties!!.get(id)
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
    fun add(prop: Property, @Context uriInfo: UriInfo): Response {
        val id = properties!!.add(prop)
        return Response.status(Response.Status.CREATED.statusCode)
                .header("Location", String.format("%s/%s", uriInfo.absolutePath.toString(), id))
                .build()
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
    fun update(prop: Property, @PathParam("id") id: Int): Response {
        properties!!.update(prop, id)
        return Response.status(Response.Status.OK.statusCode).build()
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
    fun delete(prop: Property, @PathParam("id") id: Int): Response {
        properties!!.delete(id)
        return Response.status(Response.Status.OK.statusCode).build()
    }

    @POST
    @Path("/{id}/bid")
    @Produces(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
    fun bid(bid: Bid, @PathParam("id") id: Int, @Context uriInfo: UriInfo): Response {
        val prop = properties!!.get(id)
        return Response.status(Response.Status.CREATED.statusCode)
                .entity(prop.bid(bid.offer, bid.bidder))
                .build()
    }

    companion object {
        private val LOGGER = Logger.getLogger(PropertyManager::class.java.name)
    }
}
