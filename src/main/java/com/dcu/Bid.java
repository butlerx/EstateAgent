package com.dcu;

public class Bid {
  String bidder;
  int offer;

  public Bid() {
    this("unknown", 0);
  }

  public Bid(final String bidder, final int offer) {
    this.bidder = bidder;
    this.offer = offer;
  }

  public int getOffer() {
    return this.offer;
  }

  public String getBidder() {
    return this.bidder;
  }

  public void setOffer(int offer) {
    this.offer = offer;
  }

  public void setBidder(String bidder) {
    this.bidder = bidder;
  }
}
