package com.dcu.client

import com.dcu.AlreadyBooked
import com.dcu.Bid
import com.dcu.BidTooLow
import com.dcu.Booking
import com.dcu.Property
import java.lang.System.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun main(args: Array<String>) {
    val props = PropertyManager()
    val scanner = Scanner(`in`)
    print("Enter your name: ")
    val name = scanner.nextLine()
    loop@ while (true) {
        println(
            arrayOf(
                "Choose a Command: ",
                "(1) List all Property",
                "(2) Get property by id",
                "(3) get bids for property",
                "(4) Bid on property",
                "(5) Get Bookings for property",
                "(6) Book viewing for property",
                "(7) Create Property",
                "(0) quit").joinToString(
                "\n"))
        when (scanner.nextInt()) {
            1 -> {
                println("Do You want to filter by price (y/N)")
                val filter = scanner.nextLine()
                var min = 0
                var max = 2147483647
                if ("y" == filter && "Y" == filter) {
                    print("What is your minimum price")
                    min = scanner.nextInt()
                    print("What is your maximum price")
                    max = scanner.nextInt()
                }
                println(props.getAllProperty(min, max))
            }
            2 -> {
                print("Whats the id of the property: ")
                println(props.getProperty(scanner.nextInt()))
            }
            3 -> {
                print("Whats the id of the property: ")
                println(props.getBids(scanner.nextInt()))
            }
            4 -> {
                print("Whats the id of the property: ")
                val propID = scanner.nextInt()
                var offerMade = false
                while (!offerMade) {
                    val prop = props.getProperty(propID)
                    out.printf(
                        "The Property has an asking price of %d\nThe Highest bid is %d%n",
                        prop.price, prop.bid.offer)
                    var offer = scanner.nextInt()
                    while (offer <= prop.bid.offer) {
                        print("Offer not high enough: ")
                        offer = scanner.nextInt()
                    }
                    try {
                        val bid = Bid()
                        bid.offer = offer
                        bid.bidder = name
                        props.placeBid(propID, bid)
                        offerMade = true
                    } catch (e: BidTooLow) {
                        print("Offer not high enough: ")
                    }
                }
                println("Offer made")
            }
            5 -> {
                print("Whats the id of the property: ")
                println(props.getBooking(scanner.nextInt()))
            }
            6 -> {
                print("Whats the id of the property: ")
                val propID = scanner.nextInt()
                var bookingMade = false
                // This actually results in a lot of api calls but java lambda needs the use of finals
                while (!bookingMade) {
                    val freeViewings = props.getBooking(propID)
                    out.printf("Available Viewing :\n%s%n", freeViewings)
                    val slot = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                    if (freeViewings.stream().noneMatch { viewing -> viewing === slot }) {
                        print("Booking not available")
                    } else {
                        try {
                            val booking = Booking(slot.toString())
                            booking.book(name)
                            props.makeBooking(propID, booking)
                            bookingMade = true
                        } catch (e: AlreadyBooked) {
                            print("Booking not available")
                        }
                    }
                }
                println("Booking made")
            }
            7 -> {
                print(arrayOf("Choose a property type: ", "(1) house", "(2) apartment").joinToString("\n"))
                val typeArg = scanner.nextInt()
                val type: String = when (typeArg) {
                    1 -> "house"
                    2 -> "apartment"
                    else -> {
                      println("Don't recognise type")
                      break@loop
                    }
                }
                print("What is the postcode: ")
                val postcode = scanner.nextInt()
                print("How many bedrooms does it have: ")
                val bedrooms = scanner.nextInt()
                print("What is the asking price: ")
                val price = scanner.nextInt()
                print("How many days to offer for: ")
                val days = scanner.nextInt()
                props.createProperty(Property(type, postcode, bedrooms, price, days, 0))
            }
            0 -> exit(0)
        }
    }
}