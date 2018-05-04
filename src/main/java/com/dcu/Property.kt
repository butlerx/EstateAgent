package com.dcu

import java.time.LocalDate.now
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import java.util.*

class Property @JvmOverloads constructor(
  var type: String = "house",
  var district: Int = 0,
  var bedrooms: Int = 0,
  var price: Int = 0,
  daysLeft: Int = 7,
  val id: Int = 0
) {
    val start = now()!!
    var end = now().plusDays(daysLeft.toLong())!!
        private set
    private var viewings: Array<Booking> = genViewings()
    val bid = Bid()

    var dayLeft: Int
        @PropertyView
        get() = ChronoUnit.DAYS.between(start, end).toInt()
        set(days) {
            this.end = now().plusDays(days.toLong())
            this.viewings = this.genViewings()
        }

    val freeViewings: Array<*>
        @PropertyView
        get() = Arrays.stream(this.viewings)
                .filter({ it.free })
                .map(Booking::time)
                .toArray() as Array<*>

    private fun genViewings(): Array<Booking> {
        val viewings = ArrayList<Booking>()
        var date = this.start
        while (date.isBefore(this.end)) {
            var time = LocalTime.of(8, 0)
            while (time.isBefore(LocalTime.of(18, 0))) {
                viewings.add(Booking(LocalDateTime.of(date, time).toString()))
                time = time.plusMinutes(30)
            }
            date = date.plusDays(1)
        }
        return viewings.toTypedArray()
    }

    @Throws(BidTooLow::class)
    fun bid(offer: Int, bidder: String) {
        if (offer < price || offer < this.bid.offer) throw BidTooLow()
        this.bid.offer = offer
        this.bid.bidder = bidder
    }

    fun book(time: String, booker: String) = Arrays.stream(this.viewings).filter {
      x -> time == x.time
    }.findFirst().get().book(booker)
}