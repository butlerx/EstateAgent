package com.dcu;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;

public class Property {
  private String type;
  private int id;
  private int district;
  private int bedrooms;
  private int price;
  private final LocalDate startDate = LocalDate.now();
  private LocalDate endDate = LocalDate.now().plusDays(7);
  private Booking[] viewings;
  private Bid bid = new Bid();

  public Property(
      final String type,
      final int dist,
      final int bed,
      final int price,
      final int end,
      final int id) {
    this.type = type;
    this.district = dist;
    this.bedrooms = bed;
    this.price = price;
    this.endDate = LocalDate.now().plusDays(end);
    this.id = id;
    this.genViewings();
  }

  public Property() {}

  private void genViewings() {
    ArrayList<Booking> viewings = new ArrayList<>();
    for (LocalDate date = this.startDate; date.isBefore(this.endDate); date = date.plusDays(1))
      for (LocalTime time = LocalTime.of(8, 0);
          time.isBefore(LocalTime.of(18, 0));
          time = time.plusMinutes(30)) {
        viewings.add(new Booking(LocalDateTime.of(date, time)));
      }
    this.viewings = viewings.toArray(new Booking[0]);
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

  public void setType(String type) {
    this.type = type;
  }

  public int getDistrict() {
    return this.district;
  }

  public void setDistrict(int dist) {
    this.district = dist;
  }

  public int getBedrooms() {
    return this.bedrooms;
  }

  public void setBedrooms(int bedrooms) {
    this.bedrooms = bedrooms;
  }

  public int getPrice() {
    return this.price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  @PropertyView
  public int getDayLeft() {
    return (int) ChronoUnit.DAYS.between(startDate, endDate);
  }

  public void setDaysLeft(int days) {
    this.endDate = LocalDate.now().plusDays(days);
    this.genViewings();
  }

  public int getId() {
    return id;
  }

  public void bid(final int offer, final String bidder) throws BidTooLow {
    System.out.printf("%s %s %d %d%n", offer < price, offer < this.bid.getOffer(), offer, price);
    if (offer < price || offer < this.bid.getOffer()) throw new BidTooLow();
    this.bid.setOffer(offer);
    this.bid.setBidder(bidder);
  }

  public Bid getBid() {
    return this.bid;
  }

  public void book(final LocalDateTime time, final String booker) {
    Arrays.stream(this.viewings).filter(x -> time.equals(x.time)).findFirst().get().book(booker);
  }

  @PropertyView
  public LocalDateTime[] getFreeViewings() {
    return Arrays.stream(this.viewings)
        .filter(Booking::getFree)
        .map(x -> x.time)
        .toArray(LocalDateTime[]::new);
  }
}
