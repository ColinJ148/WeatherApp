package com.example.sensorimplementation;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// Class for API request
public class FetchWeather extends AsyncTask<String, String, String> {
    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?lat=";
    private static String apiKey = "35d1d9629dd709dcbf9292faed95cc40";
    private static Double latitude;
    private static Double longitude;
    private String apiResponse;

    public FetchWeather(double longi, double lat){
        longitude = longi;
        latitude = lat;
    }

    /*API call to openweatherAPI and saves to string apiResponse*/
    @Override
    protected String doInBackground(String... url) {
        HttpURLConnection con = null;
        InputStream inputStream = null;
        try {
            con = (HttpURLConnection)
                    (new URL(BASE_URL + latitude + "&lon=" + longitude + "&APPID=" + apiKey)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();
            StringBuffer buffer = new StringBuffer();
            inputStream = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = br.readLine()) != null)
                buffer.append(line);
            inputStream.close();
            apiResponse = buffer.toString();
            return apiResponse;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.disconnect();
        }
        return apiResponse;
    }

    @Override
    protected void onPostExecute(String result){
        getWeather();
    }

    public String getWeather(){
        return apiResponse;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}
