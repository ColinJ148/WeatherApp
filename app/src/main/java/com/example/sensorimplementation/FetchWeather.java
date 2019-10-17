package com.example.sensorimplementation;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


// Class for API request
public class FetchWeather {

    private static String apiKey = "35d1d9629dd709dcbf9292faed95cc40";
    private static Double latitude = 26.614149;
    private static Double longitude = -81.825768;
    private JSONObject jsonObject;
    private String url;
    private Context context;
    //https://developer.android.com/training/volley/simple#java
    //Need to figure out context wrapper.

    public FetchWeather() {
        context = new MyContextWrapper(this);
        Log.i("FetchWeather", "Object created");
        buildUrl();
        jsonObject = volleySyncRequest(context, url);
    }
    public String getTemp(){
        return jsonObject.toString();
    }

    private JSONObject volleySyncRequest(Context c, String url) {
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        JsonObjectRequest request = new JsonObjectRequest(url, null, future, future);
        RequestQueue requestQueue = Volley.newRequestQueue(c);
        requestQueue.add(request);
        try {
            return future.get(10, TimeUnit.SECONDS);
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void buildUrl() {
        url = "api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon="
                + longitude;
    }


}
