package com.dcu;

import java.time.LocalDate;

public class Property {
  private String type;
  private int district;
  private int bedrooms;
  private int price;
  private int bid = 0;
  private LocalDate startDate;
  private LocalDate endDate;

  public Property(String type, int dist, int bed, int price) {
    this.type = type;
    this.district = dist;
    this.bedrooms = bed;
    this.price = price;
    this.startDate = LocalDate.now();
    this.endDate = LocalDate.now().plusDays(5);
  }

  @Override
  public String toString() {
    return new StringBuffer(" type: ")
        .append(this.type)
        .append(" district: ")
        .append(this.district)
        .append(" bedrooms: ")
        .append(this.bedrooms)
        .append(" price: ")
        .append(this.price)
        .append(" higestBid: ")
        .append(this.bid)
        .append(" start: ")
        .append(this.startDate)
        .append(" end: ")
        .append(this.endDate)
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
    return bedrooms;
  }

  public int getPrice() {
    return this.price;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setDistrict(int dist) {
    this.district = dist;
  }

  public void setBedrooms(int bed) {
    this.bedrooms = bed;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public void setBid(int bid) {
    this.bid = bid;
  }

  public int getBid() {
    return this.bid;
  }
}
