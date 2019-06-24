package com.example.travelexpertsapp;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import android.view.View;
import java.io.InputStream;

public class EditAccountActivity extends AppCompatActivity {
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

    Button btnUpdate, btnDelete;
    Customer customer;
    CustomerDataSource source;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        etCustomerId = findViewById(R.id.etCustomerId);
        etCustFirstName = findViewById(R.id.etCustFirstName);
        etCustLastName = findViewById(R.id.etCustLastName);
        etCustAddress = findViewById(R.id.etCustAddress);
        etCustCity = findViewById(R.id.etCustCity);
        etCustProv = findViewById(R.id.etCustProv);
        etCustPostal = findViewById(R.id.etCustPostal);
        etCustCountry = findViewById(R.id.etCustCountry);
        etCustHomePhone = findViewById(R.id.etCustHomePhone);
        etCustBusPhone = findViewById(R.id.etCustBusPhone);
        etCustEmail = findViewById(R.id.etCustEmail);
        etAgentId = findViewById(R.id.etAgentId);
        etCustUsername = findViewById(R.id.etCustUsername);
        etCustPassword = findViewById(R.id.etCustPassword);

        customer = (Customer) getIntent().getSerializableExtra("customer");
        etCustomerId.setText(customer.getCustId() + "");
        etCustFirstName.setText(customer.getCustFirstName());
        etCustLastName.setText(customer.getCustLastName());
        etCustAddress.setText(customer.getCustAddress());
        etCustCity.setText(customer.getCustCity());
        etCustProv.setText(customer.getCustProv());
        etCustPostal.setText(customer.getCustPostal());
        etCustCountry.setText(customer.getCustCountry());
        etCustHomePhone.setText(customer.getCustHomePhone());
        etCustBusPhone.setText(customer.getCustBusPhone());
        etCustEmail.setText(customer.getCustEmail());
        etAgentId.setText(customer.getAgentId() + "");
        etCustUsername.setText(customer.getCustUsername());
        etCustPassword.setText(customer.getCustPassword());



        source = new CustomerDataSource(this);
    }
}





