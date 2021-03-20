package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.Query;

public class BookDetailsActivity extends AppCompatActivity {

    TextView bookDetTitle, bookDetAuthor, bookDetISBN, bookDetDesc;
    ImageView bookDetImage;
    Button reserveBook, cancelBook, addReview;

    Query query;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
    }
}