package com.example.sensorimplementation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Weather weather;
    private TextView date_view, time_view, temp_view, pressure_view, min_temp_view, max_temp_view,
            location_view, humidity_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        init_views();
        update_time();
        try {
            weather = new Weather();
        } catch (Exception e) {
            e.printStackTrace();
        }
        set_views();
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