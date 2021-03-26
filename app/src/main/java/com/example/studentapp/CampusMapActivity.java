package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

//This activity helps the user to navigate through campus floor maps by pressing on buttons
public class CampusMapActivity extends AppCompatActivity {
    ImageView floorMap;
    Button floorGround, floorFirst, floorSecond, floorThird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_map);

        floorMap = findViewById(R.id.floorMapIv);
        floorGround = findViewById(R.id.groundFloorButton);
        floorFirst = findViewById(R.id.firstFloorButton);
        floorSecond = findViewById(R.id.secondFloorButton);
        floorThird = findViewById(R.id.thirdFloorButton);

        floorGround.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floorMap.setImageResource(R.drawable.groundfloor);
            }
        });

        floorFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floorMap.setImageResource(R.drawable.firstfloor);
            }
        });

        floorSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floorMap.setImageResource(R.drawable.secondfloor);
            }
        });

        floorThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floorMap.setImageResource(R.drawable.thirdfloor);
            }
        });

    }
}