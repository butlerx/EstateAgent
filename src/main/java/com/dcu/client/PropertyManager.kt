package com.dcu.client

import com.dcu.Bid
import com.dcu.Booking
import com.dcu.Property
import org.glassfish.jersey.jackson.JacksonFeature
import java.time.LocalDateTime
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.client.Entity
import javax.ws.rs.core.GenericType
import javax.ws.rs.core.MediaType

internal class PropertyManager {

    fun createProperty(prop: Property) {
        client
            .target(REST_URI)
            .request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(prop, MediaType.APPLICATION_JSON))
    }

    fun getProperty(id: Int): Property {
        return client
            .target(REST_URI)
            .path(id.toString())
            .request(MediaType.APPLICATION_JSON)
            .get(Property::class.java)
    }

    fun getAllProperty(min: Int, max: Int): List<Property> {
        return client
            .target(REST_URI)
            .queryParam("min", min)
            .queryParam("max", max)
            .request(MediaType.APPLICATION_JSON)
            .get(object : GenericType<List<Property>>() {})
    }

    fun getBids(id: Int): Bid {
        return client
            .target(REST_URI)
            .path(id.toString())
            .path("bid")
            .request(MediaType.APPLICATION_JSON)
            .get(Bid::class.java)
    }

    fun placeBid(id: Int, bid: Bid) {
        client
            .target(REST_URI)
            .path(id.toString())
            .path("bid")
            .request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(bid, MediaType.APPLICATION_JSON))
    }

    fun getBooking(id: Int): List<LocalDateTime> {
        return client
            .target(REST_URI)
            .path(id.toString())
            .path("booking")
            .request(MediaType.APPLICATION_JSON)
            .get(object : GenericType<List<LocalDateTime>>() {})
    }

    fun makeBooking(id: Int, booking: Booking) {
        client
            .target(REST_URI)
            .path(id.toString())
            .path("booking")
            .request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(booking, MediaType.APPLICATION_JSON))
    }

    companion object {
        private const val REST_URI = "http://localhost:8080/api/property"
        private val client = ClientBuilder.newBuilder().register(JacksonFeature::class.java).build()
    }
}