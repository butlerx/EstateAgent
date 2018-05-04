package com.dcu

import java.time.LocalDateTime

class Booking (var time: String = LocalDateTime.now().toString()) {
    var free = true
        private set
    var booker = ""

    @Throws(AlreadyBooked::class)
    fun book(booker: String): Boolean {
        if (!free) {
            throw AlreadyBooked()
        }
        this.free = false
        this.booker = booker
        return true
    }
}