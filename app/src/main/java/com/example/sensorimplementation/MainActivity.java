package com.example.sensorimplementation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Weather weather = null;
    private TextView date_view;
    private TextView time_view;
    private TextView tempurature_view;
    private TextView pressure_view;
    private TextView min_temp_view;
    private TextView max_temp_view;
    private TextView location_view;
    private TextView humditiy_view;


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

    private void init_views() {
        date_view = findViewById(R.id.date);
        time_view = findViewById(R.id.time);
        tempurature_view = findViewById(R.id.temp);
        pressure_view = findViewById(R.id.pressure_view);
        min_temp_view = findViewById(R.id.min_temp_view);
        max_temp_view = findViewById(R.id.max_temp_view);
        humditiy_view = findViewById(R.id.humitiy_view);
        location_view = findViewById(R.id.location_view);
    }

    private void update_time() {
        SetTime time = new SetTime();
        date_view.setText(time.getDate());
        time_view.setText(time.getTime());
    }

    private void set_views(){
        tempurature_view.setText("Tempurature: " + weather.getTemp());
        pressure_view.setText("Air Pressure: " + weather.getPressure());
        min_temp_view.setText("Todays Low: " + weather.getMinTemp());
        max_temp_view.setText("Todays High: " + weather.getMaxTemp());
        humditiy_view.setText("Humidity: " + weather.getHumidity());
        location_view.setText(weather.getLocation());
    }
}