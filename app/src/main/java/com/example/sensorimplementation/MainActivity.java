package com.example.sensorimplementation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private Weather weather;
    private TextView date_view, time_view, temp_view, pressure_view, min_temp_view, max_temp_view,
            location_view, humidity_view, cords_view, air_pressure_view;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private SensorManager sensorManager;
    private Sensor pressureSensor;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        init_views();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        update_time();
                        cords_view.setText(formatCords(location.getLongitude(), location.getLatitude()));
                        updateWeather(location.getLongitude(), location.getLatitude());
                        set_views();
                        if (location != null) {
                            // Logic to handle location object
                        }
                    }
                });
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                update_time();
                cords_view.setText(formatCords(location.getLongitude(), location.getLatitude()));
                updateWeather(location.getLongitude(), location.getLatitude());
                set_views();
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


        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float[] values = sensorEvent.values;
                air_pressure_view.setText(String.format("%.3f mbar", values[0]));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1,
                locationListener);
        sensorManager.registerListener(sensorEventListener, pressureSensor, SensorManager.SENSOR_DELAY_UI);
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
        cords_view = findViewById(R.id.cords);
        air_pressure_view = findViewById(R.id.air_pressure_view);
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

    private String formatCords(double longitude, double latitude) {
        String output;
        DecimalFormat df = new DecimalFormat("###.##");
        output = df.format(longitude) + ", " + df.format(latitude);
        return output;
    }

    private void updateWeather(double longitude, double latitude) {
        try {
            weather = new Weather(longitude, latitude);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}