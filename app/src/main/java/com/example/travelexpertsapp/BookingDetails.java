package com.example.travelexpertsapp;

import java.util.Date;

public class BookingDetails {
    /*
     *Purpose: BookingDetails object to hold more descriptive booking data for a specified Customer
     *Made by: Brent Ward
     * Module: CRPG 207-OSD
     *Date: June 20 2019
     */

    //properties
    private int BookingDetailId;
    private int ItineraryNo;
    private Date TripStart;
    private Date TripEnd;
    private String Description;
    private String Destination;
    private double BasePrice;
    private String RegionId;

    public BookingDetails() {}

    //getters
    public int getBookingDetailId() { return BookingDetailId; }
    public int getItineraryNo() { return ItineraryNo; }
    public Date getTripStart() { return TripStart; }
    public Date getTripEnd() { return TripEnd; }
    public String getDescription() { return Description; }
    public String getDestination() { return Destination; }
    public double getBasePrice() { return BasePrice; }
    public String getRegionId(){ return RegionId; }

    //dont need setters, this only fetches.
}
