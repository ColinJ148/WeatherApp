package com.example.sensorimplementation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private TextView date_view;
    private TextView time_view;
    private TextView tempurature_view;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init_views();
        update_time();
//        new FetchWeather().execute();
        FetchWeather fetchWeather = new FetchWeather();
        fetchWeather.execute();
        String output = fetchWeather.getWeather();


    }

    // Log.i("api", fetchWeather.doInBackground("url"));

    private void init_views() {
        date_view = findViewById(R.id.date);
        time_view = findViewById(R.id.time);
        tempurature_view = findViewById(R.id.temp);
    }

    private void update_time() {
        SetTime time = new SetTime();
        date_view.setText(time.getDate());
        time_view.setText(time.getTime());

        //Log.i("api", fetch.getTemp());
    }

//    private void get_cords() {
//
//        Location locationNet = getLastBestLocation();
//        Log.i("long", Double.toString(locationNet.getLongitude()));
//        Log.i("lat", Double.toString(locationNet.getLatitude()));
//
//
//
//
//    }

//    private Location getLastKnownLocation() {
//        LocationManager mLocationManager;
//        //Location myLocation = getLastKnownLocation();
//        mLocationManager = (LocationManager) getApplicationContext().getSystemService
//        (LOCATION_SERVICE);
//        List<String> providers = mLocationManager.getProviders(true);
//        Location bestLocation = null;
//        for (String provider : providers) {
//            @SuppressLint("MissingPermission") Location l = mLocationManager
//            .getLastKnownLocation(provider);
//            if (l == null) {
//                continue;
//            }
//            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
//                // Found best last known location: %s", l);
//                bestLocation = l;
//            }
//        }
//        return bestLocation;
//    }
//    private Location getLastBestLocation() {
//        LocationManager mLocationManager = null;
//        Location locationGPS = mLocationManager.getLastKnownLocation(LocationManager
//        .GPS_PROVIDER);
//        Location locationNet = mLocationManager.getLastKnownLocation(LocationManager
//        .NETWORK_PROVIDER);
//
//        long GPSLocationTime = 0;
//        if (null != locationGPS) { GPSLocationTime = locationGPS.getTime(); }
//
//        long NetLocationTime = 0;
//
//        if (null != locationNet) {
//            NetLocationTime = locationNet.getTime();
//        }
//
//        if ( 0 < GPSLocationTime - NetLocationTime ) {
//            return locationGPS;
//        }
//        else {
//            return locationNet;
//        }
//    }

}