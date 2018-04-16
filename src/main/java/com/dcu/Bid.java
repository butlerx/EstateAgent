package com.dcu;

@SuppressWarnings("ALL")
public class Bid {
  final String bidder;
  final int offer;

  Bid(final String bidder, final int offer) {
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
