package com.dcu;

public class Bid {
  private String bidder;
  private int offer;

  Bid(String bidder, int offer) {
    this.bidder = bidder;
    this.offer = offer;
  }

  int getOffer() {
    return this.offer;
  }

  String getBidder() {
    return this.bidder;
  }
}
