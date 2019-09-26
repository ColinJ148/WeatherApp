package com.example.sensorimplementation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private TextView date_view;
    private TextView time_view;
    private TextView tempurature_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init_views();
        update_time();
        get_cords();
    }

    private void init_views() {
        date_view = findViewById(R.id.date);
        time_view = findViewById(R.id.time);
        tempurature_view = findViewById(R.id.temp);
    }

    private void update_time() {
        SetTime time = new SetTime();
        date_view.setText(time.getDate());
        time_view.setText(time.getTime());
    }

    @SuppressLint("MissingPermission")
    private void get_cords() {


        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new MyLocationListerner();


        /*TODO This is crashing app, fix*/

//        locationManager.requestLocationUpdates(
//                LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
        //

    }

}