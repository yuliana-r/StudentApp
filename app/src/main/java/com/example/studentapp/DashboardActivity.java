package com.example.studentapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity {
    TextView campusMapTv, timetableTv, libraryTv, forumTv, activitiesTv, moodleTv;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        campusMapTv = findViewById(R.id.campusButton);
        timetableTv = findViewById(R.id.timetableButton);
        libraryTv = findViewById(R.id.libraryButton);
        forumTv = findViewById(R.id.forumButton);
        activitiesTv = findViewById(R.id.activitiesButton);
        moodleTv = findViewById(R.id.moodleButton);

        campusMapTv.setOnClickListener((v) -> {
            Intent i = new Intent(DashboardActivity.this, CampusMapActivity.class);
            startActivity(i);
        });

        libraryTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, LibraryActivity.class);
                startActivity(i);
            }
        });
















        moodleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://partnerships.moodle.roehampton.ac.uk/login/index.php"));
                startActivity(i);
            }
        });


        logoutButton = findViewById(R.id.logOutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}




