package com.example.travelexpertsapp;

import android.os.AsyncTask;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travelexpertsapp.helpers.custValidate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    EditText txtUsername;
    EditText txtPassword;
    Button btnLogin;

    String ipAddress = "10.163.112.5";

    StringBuffer buffer = new StringBuffer();

    boolean loggedIn = false;
     //int custId = 143;
     int custId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = findViewById(R.id.txtUserName);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();

                if(custValidate.isValidString(username) &&
                        custValidate.isValidString(password)){

                    CustomerLogin customer = new CustomerLogin(
                            0,
                                username,
                                password);

                    new LoginCustomer().execute(customer);

                    if (custId != 0){

                        loggedIn = true;

                        if(loggedIn)
                        {
                            Intent intent = new Intent(getApplicationContext(), MyAccountActivity.class);
                            intent.putExtra("custId", custId);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    if(custValidate.fieldError.size() != 0) {

                        if (username.trim().isEmpty()) {
                            Toast.makeText(LoginActivity.this, custValidate.fieldError.get(username), Toast.LENGTH_SHORT).show();
                        }

                        if (password.trim().isEmpty()) {
                            Toast.makeText(LoginActivity.this, custValidate.fieldError.get(password), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Error! Please try again!", Toast.LENGTH_SHORT).show();
                    }
                }
            };
        });
    }

    private int getCustId(Customer cust){

        return cust.getCustomerId();
    }

    class LoginCustomer extends AsyncTask<CustomerLogin, String, String>
    {

        @Override
        protected String doInBackground(CustomerLogin... customerLogins) {
            BufferedWriter bw = null;
            BufferedReader br = null;

            try{

                JSONObject postData = new JSONObject();

                postData.put("custUsername", customerLogins[0].getCustUsername());
                postData.put("custPassword", customerLogins[0].getCustPassword());

                URL url = new URL("http://" + ipAddress + ":8080/Team3-JSPWebService/rest/loginAPI/custLogin");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");

                conn.setDoInput(false);
                conn.setDoOutput(true);

                bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
                bw.write(String.valueOf(postData));
                bw.flush();

                int statusCode = conn.getResponseCode();

                if (statusCode == 200){

                    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    String jsonCust;

                    while ((jsonCust = br.readLine()) != null)
                    {
                        buffer.append(jsonCust);
                    }

                }
                else{
                    buffer.append("Error: " + statusCode);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try
                {

                    if(br != null){
                        br.close();
                    }
                    if(bw != null ){
                        bw.close();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return buffer.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONArray custArray = new JSONArray(buffer.toString());

                JSONObject cust = custArray.getJSONObject(0);

                Customer customer = new Customer(
                        cust.getInt("customerId"),
                        cust.getString("custFirstName"),
                        cust.getString("custLastName"),
                        cust.getString("custAddress"),
                        cust.getString("custCity"),
                        cust.getString("custProv"),
                        cust.getString("custPostal"),
                        cust.getString("custCountry"),
                        cust.getString("custHomePhone"),
                        cust.getString("custBusPhone"),
                        cust.getString("custEmail"),
                        cust.getInt("agentId"),
                        cust.getString("custUsername"),
                        cust.getString("custPassword")
                );

                custId = getCustId(customer);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
