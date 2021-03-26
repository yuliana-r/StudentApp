package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class ActivitiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        //Creating an image slider and setting the images for each slide from R.drawable

        ImageSlider imageSlider = findViewById(R.id.activitiesSlider);
        List<SlideModel> imageList = new ArrayList<SlideModel>();

        imageList.add(new SlideModel(R.drawable.march_activities, ScaleTypes.CENTER_INSIDE));
        imageList.add(new SlideModel(R.drawable.april_activities, ScaleTypes.CENTER_INSIDE));
        imageList.add(new SlideModel(R.drawable.may_activities, ScaleTypes.CENTER_INSIDE));
        imageSlider.setImageList(imageList);

    }
}