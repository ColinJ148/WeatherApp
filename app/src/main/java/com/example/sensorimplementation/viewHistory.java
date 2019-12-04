package com.example.sensorimplementation;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class viewHistory extends AppCompatActivity {
    String TAG = "DEBUG";
    private TextView historyText;
    private DatabaseReference weatherdb;
    List<Weather> weatherData = new ArrayList<Weather>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_history);
        historyText = findViewById(R.id.history_output);
        weatherdb = FirebaseDatabase.getInstance().getReference().child("weather");


    }

    @Override
    public void onStart() {
        super.onStart();
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                Weather weather = new Weather();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    /*TODO figure out null pointer problem*/
                    weather = postSnapshot.getValue(Weather.class);
                    weatherData.add(weather);
                }
//                dataSnapshot.getChildren();
//                weather = dataSnapshot.getValue(Weather.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        weatherdb.addValueEventListener(postListener);

    }


}
