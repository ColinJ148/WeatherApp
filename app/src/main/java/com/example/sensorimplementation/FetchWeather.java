package com.example.sensorimplementation;


import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


// Class for API request
public class FetchWeather extends AsyncTask<String, Void, String> {
    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?lat=";
    private static String apiKey = "35d1d9629dd709dcbf9292faed95cc40";
    private static Double latitude = 26.614149;
    private static Double longitude = -81.825768;
    private String apiResponse;
    //https://developer.android.com/training/volley/simple#java
    //Need to figure out context wrapper.

    public FetchWeather() {
        Log.i("FetchWeather", "Object created");

    }

    @Override
    protected String doInBackground(String... url) {
        HttpURLConnection con = null;
        InputStream inputStream = null;
        try {
            con = (HttpURLConnection) (new URL(BASE_URL + latitude + "&lon=" + longitude + "&APPID=" + apiKey)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();
            StringBuffer buffer = new StringBuffer();
            inputStream = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = br.readLine()) != null)
                buffer.append(line + "rn");

            inputStream.close();
            con.disconnect();
            apiResponse = buffer.toString();
            return apiResponse;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return apiResponse;
        }

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }


}
