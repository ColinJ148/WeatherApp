package com.example.sensorimplementation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
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
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        init_views();
        update_time();
        Weather weather = null;
        try {
            weather = new Weather();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tempurature_view.setText("Tempurature: " + weather.getTemp());
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
}