package com.example.travelexpertsapp;

import java.io.Serializable;

public class CustomerLogin implements Serializable {
    /*
     * Author: Ibraheem Kolawole
     * Purpose: Customer Login object to hold login data for a specified Customer
     * Date: June 20 2019
     */
    private int customerId;

    private String custUsername;
    private String custPassword;

    public CustomerLogin(int customerId, String custUsername, String custPassword) {
        this.customerId = customerId;
        this.custUsername = custUsername;
        this.custPassword = custPassword;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustUsername() {
        return custUsername;
    }

    public void setCustUsername(String custUsername) {
        this.custUsername = custUsername;
    }

    public String getCustPassword() {
        return custPassword;
    }

    public void setCustPassword(String custPassword) {
        this.custPassword = custPassword;
    }

}
