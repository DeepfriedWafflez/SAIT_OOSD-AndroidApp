package com.example.travelexpertsapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;

public class BookingsActivity extends AppCompatActivity {
    /*
     *Purpose: Populate the Bookings Activity with customers bookings
     *Made by: Brent Ward
     * Module: CRPG 207-OSD
     *Date: June 20 2019
     */
    ListView lvBookings;
    String ipAddress = "10.163.112.140";

    StringBuffer buffer = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        lvBookings = findViewById(R.id.lvBookings);

        new GetBookings().execute();

        lvBookings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), BookingDetailsActivity.class);
                intent.putExtra("booking", (Booking) lvBookings.getItemAtPosition(position));
                startActivity(intent);
            }
        });
    }

    class GetBookings extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                URL url = new URL("http://" + ipAddress + ":8080/Team3-JSPWebService/rest/bookings/getbookings/" + 148);
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
            ArrayAdapter<Booking> adapter = new ArrayAdapter<Booking>(getApplicationContext(),android.R.layout.simple_list_item_1);
            try {
                JSONArray jsonArray = new JSONArray(buffer.toString());
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject bk = jsonArray.getJSONObject(i);
                    Date dateHolder = new Date(bk.getString("bookingDate"));
                    Booking booking = new Booking(bk.getInt("bookingId"), dateHolder, bk.getString("bookingNo"),
                                                    bk.getInt("travelerCount"), bk.getString("tripTypeId"));
                    adapter.add(booking);
                }
                lvBookings.setAdapter(adapter);
            } catch (JSONException e) { e.printStackTrace(); }
        }
    }
}
