package com.example.travelexpertsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText txtUsername;
    EditText txtPassword;
    Button btnLogin;
    boolean loggedIn = false;
    int custId = 143;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = findViewById(R.id.txtUserName);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //INSERT WEB SERVICE LOGIN CODE HERE - SET loggedIn as TRUE
                loggedIn = true;
                if(loggedIn){
                    Intent intent = new Intent(getApplicationContext(), MyAccountActivity.class);
                    intent.putExtra("custId", custId);
                    startActivity(intent);
                }
            }
        });
    }
}
