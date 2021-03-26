package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

//This activity is used to display student's timetable for current semester

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