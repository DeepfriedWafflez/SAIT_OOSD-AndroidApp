package com.example.travelexpertsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MyAccountActivity extends AppCompatActivity {

    EditText etCustomerId,
            etCustFirstName,
            etCustLastName,
            etCustAddress,
            etCustCity,
            etCustProv,
            etCustPostal,
            etCustCountry,
            etCustHomePhone,
            etCustBusPhone,
            etCustEmail,
            etAgentId,
            etCustUsername,
            etCustPassword;

    Button btnEdit, btnBookings;

    Customer customer;
    int testid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        Intent intent = getIntent();
        testid = intent.getIntExtra("custId", 0);


        etCustomerId = findViewById(R.id.etCustomerId);
        etCustFirstName = findViewById(R.id.etCustFirstName);
        etCustLastName = findViewById(R.id.etCustLastName);
        etCustAddress = findViewById(R.id.etCustAddress);
        etCustCity = findViewById(R.id.etCustCity);
        etCustProv = findViewById(R.id.etCustProv);
        etCustPostal = findViewById(R.id.etCustPostal);
        etCustCountry = findViewById(R.id.etCustCountry);
        etCustEmail = findViewById(R.id.etCustEmail);
        etCustHomePhone = findViewById(R.id.etCustHomePhone);
        etCustBusPhone = findViewById(R.id.etCustBusPhone);
        etCustEmail = findViewById(R.id.etCustHomePhone);
        etAgentId = findViewById(R.id.etAgentId);
        etCustUsername = findViewById(R.id.etCustUsername);
        etCustPassword = findViewById(R.id.etCustPassword);

        btnEdit = findViewById(R.id.btnEdit);
        btnBookings = findViewById(R.id.btnBooking);

        btnBookings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BookingsActivity.class);
                intent.putExtra("custId", testid);
                startActivity(intent);
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), EditAccountActivity.class);
                    intent.putExtra("customer", customer);
                    startActivity(intent);
            }
        });
    }
}
