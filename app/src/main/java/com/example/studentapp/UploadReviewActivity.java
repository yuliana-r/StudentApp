package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class UploadReviewActivity extends AppCompatActivity {

    EditText reviewAuthor, reviewDesc;
    Button addReview;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_review);

        reviewAuthor = findViewById(R.id.addReviewAuthorEt);
        reviewDesc = findViewById(R.id.addReviewEt);
        addReview = findViewById(R.id.addReviewButton);
        databaseReference = FirebaseDatabase.getInstance().getReference("book_reviews");
        final String bookId = databaseReference.push().getKey();

        addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Review review = new Review(reviewAuthor.getText().toString(), reviewDesc.getText().toString(), bookId);
                databaseReference.child(bookId).setValue(review);
                Toast.makeText(UploadReviewActivity.this, "Your review has been added successfully",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}