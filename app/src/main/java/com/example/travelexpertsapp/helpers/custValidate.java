package com.example.travelexpertsapp.helpers;
import java.util.ArrayList;
import java.util.HashMap;


public class custValidate {


    public static ArrayList<String> validatedData = new ArrayList<>();

    public static ArrayList<String> invalidData = new ArrayList<>();

    public static HashMap<String, String> fieldError = new HashMap<String, String>();


    public static Boolean isValidString(String str) {

        if (str.isEmpty() || str.equals("") || str.trim().isEmpty() || str.trim().equals("")) {
            fieldError.put(str, "This field is required");
            invalidData.add(str);
            return false;
        } else {
            validatedData.add(str);
            invalidData.clear();
            fieldError.remove(str);
            return true;
        }
    }

}
