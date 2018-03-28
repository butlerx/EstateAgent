package com.dcu;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Property {
  private String type;
  private int id = 0;
  private int district;
  private int bedrooms;
  private int price;
  private int highestBid = 0;
  private String bidder = "";
  private final LocalDate startDate = LocalDate.now();
  private LocalDate endDate;

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
    return new StringBuffer("{\"type\": \"")
        .append(this.type)
        .append("\", \"district\": ")
        .append(this.district)
        .append(", \"bedrooms\": ")
        .append(this.bedrooms)
        .append(", \"price\": ")
        .append(this.price)
        .append(", \"higestBid\": ")
        .append(this.highestBid)
        .append(", \"bidder\": \"")
        .append(this.bidder)
        .append("\", \"start\": \"")
        .append(this.startDate)
        .append("\", \"end\": \"")
        .append(this.endDate)
        .append("\"}")
        .toString();
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
