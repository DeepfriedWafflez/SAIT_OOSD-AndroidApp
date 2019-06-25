package com.example.travelexpertsapp;

import java.io.Serializable;
import java.util.Date;

public class Booking implements Serializable {
    /*
     *Purpose: Booking object to hold booking data for a specified Customer
     *Made by: Brent Ward
     * Module: CRPG 207-OSD
     *Date: June 20 2019
     */

    //properties
    private int BookingId;
    private Date BookingDate;
    private String BookingNo;
    private int TravelerCount;
    private String TripTypeId;

    public Booking() {}
    public Booking(int bookingId, Date bookingDate, String bookingNo, int travelerCount, String tripTypeId){
        this.BookingId = bookingId;
        this.BookingDate = bookingDate;
        this.BookingNo = bookingNo;
        this.TravelerCount = travelerCount;
        this.TripTypeId = tripTypeId;
    }

    //getters
    public int getBookingId() { return BookingId; }
    public Date getBookingDate() { return BookingDate; }
    public String BookingNo() { return BookingNo; }
    public int TravelerCount() { return TravelerCount; }
    public String TripTypeId() { return TripTypeId; }

    //dont need setters, its only a fetching display.


    @Override
    public String toString() {
        return BookingNo;
    }
}
