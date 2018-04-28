package com.dcu

import java.time.LocalDateTime

class Booking (var time: LocalDateTime = LocalDateTime.now()) {
    var free = true
        private set
    var booker = ""

    @Throws(AlreadyBooked::class)
    fun book(booker: String) {
        if (!free) {
            throw AlreadyBooked()
        }
        this.free = false
        this.booker = booker
    }
}