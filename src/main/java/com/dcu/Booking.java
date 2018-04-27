package com.dcu;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class Booking {

  private boolean free = true;
  private String booker = "";

  @DateTimeFormat(iso = DATE_TIME)
  public LocalDateTime time;

  public Booking(LocalDateTime time) {
    this.time = time;
  }

  public void book(final String booker) throws AlreadyBooked {
    if (!free) {
      throw new AlreadyBooked();
    }
    this.free = false;
    this.booker = booker;
  }

  public boolean getFree() {
    return this.free;
  }

  public String getBooker() {
    return this.booker;
  }

  public Booking() {}

  public void setBooker(String booker) {
    this.booker = booker;
  }

  public void setTime(LocalDateTime time) {
    this.time = time;
  }
}
