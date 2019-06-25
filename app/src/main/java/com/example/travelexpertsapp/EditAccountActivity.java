package com.example.travelexpertsapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class EditAccountActivity extends AppCompatActivity {
    String ipAddress;
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

    Button btnSave, btnBack;
    //Customer customer;
   // CustomerDataSource source;

    //StringBuffer buffer = new StringBuffer();
    //Booking selectedBooking;
    //BookingDetails details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        btnBack = findViewById(R.id.btnBack);
        btnSave = findViewById(R.id.btnSave);

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

        Intent intent = getIntent();
        Customer customer = (Customer) getIntent().getSerializableExtra("customer");
        ipAddress = intent.getStringExtra("ipaddress");


        etCustomerId.setText(customer.getCustomerId() + "");
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

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //build customer object and pass to asynctask
                Customer customer = new Customer(
                        Integer.parseInt(etCustomerId.getText().toString()),
                        etCustFirstName.getText().toString(),
                        etCustLastName.getText().toString(),
                        etCustAddress.getText().toString(),
                        etCustCity.getText().toString(),
                        etCustProv.getText().toString(),
                        etCustPostal.getText().toString(),
                        etCustCountry.getText().toString(),
                        etCustEmail.getText().toString(),
                        etCustHomePhone.getText().toString(),
                        etCustBusPhone.getText().toString(),
                        Integer.parseInt(etAgentId.getText().toString()),
                        etCustUsername.getText().toString(),
                        etCustPassword.getText().toString());
                new UpdateCustomer().execute(customer);
            }
    });
    }

    class UpdateCustomer extends AsyncTask<Customer, String, String>
    {
        @Override
        protected String doInBackground(Customer... customer) {
            BufferedWriter bw = null;
            BufferedReader br = null;
            StringBuffer buffer = new StringBuffer();
            try {
                JSONObject postData = new JSONObject();
                postData.put("customerId", customer[0].getCustomerId());
                postData.put("custFirstName", customer[0].getCustFirstName());
                postData.put("custLastName", customer[0].getCustLastName());
                postData.put("custAddress", customer[0].getCustAddress());
                postData.put("custCity", customer[0].getCustCity());
                postData.put("custProv", customer[0].getCustProv());
                postData.put("custCountry", customer[0].getCustCountry());
                postData.put("custPostal", customer[0].getCustPostal());
                postData.put("custEmail", customer[0].getCustEmail());
                postData.put("custHomePhone", customer[0].getCustHomePhone());
                postData.put("custBusPhone", customer[0].getCustBusPhone());
                postData.put("agentId", customer[0].getAgentId());
                postData.put("custUsername", customer[0].getCustUsername());
                postData.put("custPassword", customer[0].getCustPassword());

                Log.d("TravelExperts", "doInBackground: " + postData);

                URL url = new URL("http://" + ipAddress +
                        ":8080/Team3-JSPWebService/rest/customers/postcustomer");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
                bw.write(String.valueOf(postData));
                bw.flush();
                int statusCode = conn.getResponseCode();
                Log.d("TravelExperts", "The status code is " + statusCode);

                if (statusCode == 200 ){
                    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String message;
                    while ((message = br.readLine()) != null) {
                        buffer.append(message);
                    }
                    Log.d("this", "The response is " + buffer.toString());
                    }else {
                    buffer.append("Error:" + statusCode);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                    if (bw != null) {
                        bw.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return buffer.toString();
        }

@Override
protected void onPostExecute(String message)
{
super.onPostExecute(message);
    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }
    }
}







