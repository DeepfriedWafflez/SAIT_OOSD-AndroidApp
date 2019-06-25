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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);

        //grabs the intent (session)
        selectedBooking = (Booking) getIntent().getSerializableExtra("booking");

        new GetBookingDetails().execute();

    }

    private void fillTextFields(BookingDetails savedDetails){
        //linking objects
        txtItineraryNo = findViewById(R.id.txtItineraryNo);
        txtTripStart = findViewById(R.id.txtTripStart);
        txtTripEnd= findViewById(R.id.txtTripEnd);
        txtDescription = findViewById(R.id.txtDescription);
        txtDestination = findViewById(R.id.txtDestination);
        txtRegionId = findViewById(R.id.txtRegionId);
        txtBasePrice = findViewById(R.id.txtBasePrice);
        txtTotalCost = findViewById(R.id.txtTotalCost);

        //fills the text fields
        txtItineraryNo.setText(Integer.toString(savedDetails.getItineraryNo()));
        txtTripStart.setText(savedDetails.getTripStart().toString());
        txtTripEnd.setText(savedDetails.getTripEnd().toString());
        txtDescription.setText(savedDetails.getDescription());
        txtDestination.setText(savedDetails.getDestination());
        txtRegionId.setText(savedDetails.getRegionId());
        txtBasePrice.setText(Double.toString(savedDetails.getBasePrice()));
        double totalCost = selectedBooking.TravelerCount() * savedDetails.getBasePrice();
        txtTotalCost.setText(Double.toString(totalCost));
    }

    class GetBookingDetails extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("http://10.163.112.6:8080/Team3-JSPWebService/rest/bookings/getbookingdetails/" + selectedBooking.getBookingId());
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
                JSONArray bdarray = new JSONArray(buffer.toString());
                JSONObject bd = bdarray.getJSONObject(0);
                Date startDate = new Date(bd.getString("tripStart"));
                Date endDate = new Date(bd.getString("tripEnd"));
                BookingDetails details = new BookingDetails(bd.getInt("bookingDetailId"), bd.getInt("itineraryNo"), startDate,
                        endDate ,bd.getString("description"), bd.getString("destination"),
                        bd.getDouble("basePrice"), bd.getString("regionId"));
                fillTextFields(details);
            } catch (JSONException e) { e.printStackTrace(); }
        }
    }
}
