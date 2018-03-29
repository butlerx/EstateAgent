package com.dcu;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Property {
  private final String type;
  private final int id;
  private final int district;
  private final int bedrooms;
  private final int price;
  private final LocalDate startDate = LocalDate.now();
  private final LocalDate endDate;
  private final Booking[] viewings;
  private int highestBid = 0;
  private String bidder = "";

  public Property(String type, int dist, int bed, int price, int end, int id) {
    this.type = type;
    this.district = dist;
    this.bedrooms = bed;
    this.price = price;
    this.endDate = LocalDate.now().plusDays(end);
    this.id = id;
    List<Booking> viewings = new ArrayList<>();
    for (LocalDate date = this.startDate; date.isBefore(this.endDate); date = date.plusDays(1)) {
      for (LocalTime time = LocalTime.of(8, 0);
          time.isBefore(LocalTime.of(18, 0));
          time = time.plusMinutes(30)) {
        viewings.add(new Booking(LocalDateTime.of(date, time)));
      }
    }
    this.viewings = viewings.toArray(new Booking[viewings.size()]);
  }

  public LocalDate getStart() {
    return this.startDate;
  }

  public LocalDate getEnd() {
    return this.endDate;
  }

  public String getType() {
    return this.type;
  }

  public int getDistrict() {
    return this.district;
  }

  public int getBedrooms() {
    return this.bedrooms;
  }

  public int getPrice() {
    return this.price;
  }

  @PropertyView
  public int getDayLeft() {
    return (int) ChronoUnit.DAYS.between(startDate, endDate);
  }

  public int getId() {
    return id;
  }

  public void bid(int offer, String bidder) {
    if (offer < price || offer < this.highestBid) throw new BidTooLow();
    this.highestBid = offer;
    this.bidder = bidder;
  }

  public Bid getBid() {
    return new Bid(this.bidder, this.highestBid);
  }

  public void book(LocalDateTime time, String booker) {
    Booking slot = Arrays.stream(this.viewings).filter(x -> time.equals(x.time)).findFirst().get();
    try {
      slot.book(booker);
    } catch (AlreadyBooked e) {
      throw e;
    }
  }

  @PropertyView
  public LocalDateTime[] getFreeViewings() {
    return Arrays.stream(this.viewings)
        .filter(x -> x.getFree())
        .map(x -> x.time)
        .toArray(LocalDateTime[]::new);
  }
}
