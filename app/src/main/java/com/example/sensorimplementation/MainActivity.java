package com.example.sensorimplementation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
    private Weather weather;
    private TextView date_view, time_view, temp_view, pressure_view, min_temp_view, max_temp_view,
            location_view, humidity_view;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
//        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }

            @Override
            public void onProviderEnabled(String s) {
            }

            @Override
            public void onProviderDisabled(String s) {
            }
        };
        init_views();
        update_time();
        try {
            weather = new Weather();
        } catch (Exception e) {
            e.printStackTrace();
        }
        set_views();
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 100,
                locationListener);
        String output = Double.toString(latitude);
        Log.i("latitude", Double.toString(latitude));
        Log.i("long", Double.toString(longitude));


    }


    /*initializes views*/
    private void init_views() {
        date_view = findViewById(R.id.date);
        time_view = findViewById(R.id.time);
        temp_view = findViewById(R.id.temp);
        pressure_view = findViewById(R.id.pressure_view);
        min_temp_view = findViewById(R.id.min_temp_view);
        max_temp_view = findViewById(R.id.max_temp_view);
        humidity_view = findViewById(R.id.humitiy_view);
        location_view = findViewById(R.id.location_view);
    }

    /*Method that updates the time displayed on the UI, going to change to system clock*/
    private void update_time() {
        SetTime time = new SetTime();
        date_view.setText(time.getDate());
        time_view.setText(time.getTime());

    }

    /*Method that sets views using weather object*/
    private void set_views() {
        temp_view.setText(weather.getTemp() + "\u00B0");
        pressure_view.setText(weather.getPressure() + " hPa");
        min_temp_view.setText(weather.getMinTemp() + "\u00B0");
        max_temp_view.setText(weather.getMaxTemp() + "\u00B0");
        humidity_view.setText(weather.getHumidity() + "%");
        location_view.setText(weather.getLocation());
    }

}