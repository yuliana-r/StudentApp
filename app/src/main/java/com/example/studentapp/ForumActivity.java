package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ForumActivity extends AppCompatActivity {

    private Button latestNews, academicBtn, offTopicBtn, rNgBtn, faqBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        latestNews = findViewById(R.id.latestNewsButton);
        academicBtn = findViewById(R.id.academicButton);
        offTopicBtn = findViewById(R.id.offTopicButton);
        rNgBtn = findViewById(R.id.rulesGuidesButton);
        faqBtn = findViewById(R.id.faqsButton);

        latestNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForumActivity.this, ForumLatestNewsActivity.class);
                startActivity(intent);
            }
        });

        academicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForumActivity.this, ForumAcademicActivity.class);
                startActivity(intent);
            }
        });

        offTopicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForumActivity.this, ForumOffTopicActivity.class);
                startActivity(intent);
            }
        });

        rNgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForumActivity.this, ForumRulesGuidesActivity.class);
                startActivity(intent);
            }
        });

        faqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForumActivity.this, ForumFAQActivity.class);
                startActivity(intent);
            }
        });


    }
}