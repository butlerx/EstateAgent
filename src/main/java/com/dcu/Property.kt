package com.dcu

import java.time.LocalDate
import java.time.temporal.ChronoUnit

class Property(val type: String, val district: Int, val bedrooms: Int, val price: Int, end: Int, val id: Int) {
    var bid = 0
        private set
    var bidder = ""
        private set
    val start = LocalDate.now()
    val end: LocalDate = LocalDate.now().plusDays(end.toLong())

  val dayLeft: Int
        get() = ChronoUnit.DAYS.between(start, end).toInt()

  override fun toString(): String {
        return ("{\"type\": \""
                + this.type
                + "\", \"district\": "
                + this.district
                + ", \"bedrooms\": "
                + this.bedrooms
                + ", \"price\": "
                + this.price
                + ", \"higestBid\": "
                + this.bid
                + ", \"bidder\": \""
                + this.bidder
                + "\", \"start\": \""
                + this.start
                + "\", \"end\": \""
                + this.end
                + "\"}")
    }

    fun bid(offer: Int, bidder: String): Boolean {
        if (offer < price) return false
        if (offer > this.bid) {
            this.bid = offer
            this.bidder = bidder
            return true
        }
        return false
    }
}
