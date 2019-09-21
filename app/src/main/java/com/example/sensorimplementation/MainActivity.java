package com.example.sensorimplementation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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