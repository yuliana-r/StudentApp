package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

//This class is used for uploading reviews for library books
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

        //Getting the database reference to save books into the "book_reviews" node
        databaseReference = FirebaseDatabase.getInstance().getReference("book_reviews");
        final String bookId = databaseReference.push().getKey();


        addReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating a new Review object with the book ID, author name and review)
                Review review = new Review(bookId, reviewAuthor.getText().toString(), reviewDesc.getText().toString());
                databaseReference.child(bookId).setValue(review);
                Toast.makeText(UploadReviewActivity.this, "Your review has been added successfully",
                        Toast.LENGTH_LONG).show();
                Intent i = new Intent(UploadReviewActivity.this, BookDetailsActivity.class);
                startActivity(i);
            }
        });
    }
}