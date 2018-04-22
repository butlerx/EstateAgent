package com.dcu;

public class Bid {
  final String bidder;
  final int offer;

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
}
