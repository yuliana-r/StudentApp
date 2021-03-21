package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class TimetableActivity extends AppCompatActivity {
    ImageView timetable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        timetable = findViewById(R.id.imageTimetable);
        timetable.setImageResource(R.drawable.timetable);
    }
}