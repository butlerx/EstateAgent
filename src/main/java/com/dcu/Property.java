package com.dcu;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Property {
  private final String type;
  private final int id;
  private final int district;
  private final int bedrooms;
  private final int price;
  private int highestBid = 0;
  private String bidder = "";
  private final LocalDate startDate = LocalDate.now();
  private final LocalDate endDate;

  public Property(String type, int dist, int bed, int price, int end, int id) {
    this.type = type;
    this.district = dist;
    this.bedrooms = bed;
    this.price = price;
    this.endDate = LocalDate.now().plusDays(end);
    this.id = id;
  }

  @Override
  public String toString() {
    return "{\"type\": \""
        + this.type
        + "\", \"district\": "
        + this.district
        + ", \"bedrooms\": "
        + this.bedrooms
        + ", \"price\": "
        + this.price
        + ", \"higestBid\": "
        + this.highestBid
        + ", \"bidder\": \""
        + this.bidder
        + "\", \"start\": \""
        + this.startDate
        + "\", \"end\": \""
        + this.endDate
        + "\"}";
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

  public int getBid() {
    return this.highestBid;
  }

  public String getBidder() {
    return this.bidder;
  }

  public int getDayLeft() {
    return (int) ChronoUnit.DAYS.between(startDate, endDate);
  }

  public int getId() {
    return id;
  }

  public boolean bid(int offer, String bidder) {
    if (offer < price) return false;
    if (offer > this.highestBid) {
      this.highestBid = offer;
      this.bidder = bidder;
      return true;
    }
    return false;
  }
}
