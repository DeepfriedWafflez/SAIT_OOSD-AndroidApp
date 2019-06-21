package com.example.travelexpertsapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;

public class BookingDetailsActivity extends AppCompatActivity {
    /*
     *Purpose: Populate the Bookings Activity with customers bookings
     *Made by: Brent Ward
     * Module: CRPG 207-OSD
     *Date: June 20 2019
     */

    //properties
    TextView txtItineraryNo;
    TextView txtTripStart;
    TextView txtTripEnd;
    TextView txtDescription;
    TextView txtDestination;
    TextView txtRegionId;
    TextView txtBasePrice;
    TextView txtTotalCost;

    StringBuffer buffer = new StringBuffer();
    Booking selectedBooking;
    BookingDetails details;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        //linking objects
        txtItineraryNo = findViewById(R.id.txtItineraryNo);
        txtTripStart = findViewById(R.id.txtTripStart);
        txtTripEnd= findViewById(R.id.txtTripEnd);
        txtDescription = findViewById(R.id.txtDescription);
        txtDestination = findViewById(R.id.txtDestination);
        txtRegionId = findViewById(R.id.txtRegionId);
        txtBasePrice = findViewById(R.id.txtBasePrice);
        txtTotalCost = findViewById(R.id.txtTotalCost);

        //grabs the intent (session)
        selectedBooking = (Booking) getIntent().getSerializableExtra("booking");

        new GetBookingDetails().execute();

        //fills the text fields
        txtItineraryNo.setText(details.getItineraryNo());
        txtTripStart.setText(details.getTripStart().toString());
        txtTripEnd.setText(details.getTripEnd().toString());
        txtDescription.setText(details.getDescription());
        txtDestination.setText(details.getDestination());
        txtRegionId.setText(details.getRegionId());
        txtBasePrice.setText(Double.toString(details.getBasePrice()));
        double totalCost = selectedBooking.TravelerCount() * details.getBasePrice();
        txtTotalCost.setText(Double.toString(totalCost));
    }

    class GetBookingDetails extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("http://10.163.112.39:8080/Team3-JSPWebService/rest/bookings/getbookingdetails/" + selectedBooking.BookingNo());
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                String json;
                while((json = br.readLine()) != null) buffer.append(json);
                br.close();
            } catch (IOException e) { e.printStackTrace();}
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                JSONObject bd = new JSONObject(buffer.toString());
                Date startDate = new Date(bd.getString("tripStart"));
                Date endDate = new Date(bd.getString("tripEnd"));
                details = new BookingDetails(bd.getInt("bookingDetailsId"), bd.getInt("itineraryNo"), startDate,
                        endDate ,bd.getString("description"), bd.getString("destination"),
                        bd.getDouble("basePrice"), bd.getString("regionId"));
            } catch (JSONException e) { e.printStackTrace(); }
        }
    }
}
