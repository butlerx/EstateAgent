package com.dcu;

import java.time.LocalDateTime;

class Booking {

  private boolean free = true;
  private String booker = "";
  public final LocalDateTime time;

  Booking(LocalDateTime time) {
    this.time = time;
  }

  public void book(final String booker) {
    if (!free) throw new AlreadyBooked();
    this.free = false;
    this.booker = booker;
  }

  public boolean getFree() {
    return this.free;
  }

  public String getBooker() {
    return this.booker;
  }
}
