package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.studentapp.Review.getBookID;

//Activity that displays information/details about a chosen book in the library
public class BookDetailsActivity extends AppCompatActivity {

    TextView bookDetTitle, bookDetAuthor, bookDetISBN, bookDetDesc;
    ImageView bookDetImage;
    Button reserveBook, cancelBook, addReview;

    Query query;
    RecyclerView recyclerView;
    ReviewAdaptor reviewAdaptor;
    RecyclerView.LayoutManager manager;

    ArrayList<Review> reviews = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        Intent i = getIntent();
        Book book = i.getParcelableExtra("Book");
        recyclerView = findViewById(R.id.recyclerViewReviews);
        bookDetTitle = findViewById(R.id.bookDetailsTitle);
        bookDetAuthor = findViewById(R.id.bookDetailsAuthor);
        bookDetISBN = findViewById(R.id.bookDetailsISBN);
        bookDetDesc = findViewById(R.id.bookDetailsDesc);
        bookDetImage = findViewById(R.id.bookDetailsImage);
        reserveBook = findViewById(R.id.reserveBookButton);
        cancelBook = findViewById(R.id.cancelReservationButton);
        addReview = findViewById(R.id.addReviewButton);

        //Retrieves book information
        bookDetTitle.setText(book.getBookTitle());
        bookDetAuthor.setText(book.getBookAuthor());
        bookDetISBN.setText(book.getBookISBN());
        bookDetDesc.setText(book.getBookDescription());
        Picasso.get().load(book.getBookImgUrl()).fit().into(bookDetImage);
        cancelBook.setVisibility(View.INVISIBLE);

        reserveBook.setOnClickListener(v -> {
            cancelBook.setVisibility(View.VISIBLE);
            reserveBook.setVisibility(View.INVISIBLE);
            Toast.makeText(BookDetailsActivity.this, "You have successfully reserved the book.", Toast.LENGTH_LONG).show();
        });

        cancelBook.setOnClickListener(v -> {
            cancelBook.setVisibility(View.INVISIBLE);
            reserveBook.setVisibility(View.VISIBLE);
            Toast.makeText(BookDetailsActivity.this, "You have successfully cancelled the reservation.", Toast.LENGTH_LONG).show();
        });

        //Takes user to UploadReviewActivity
        addReview.setOnClickListener(v -> {
            Intent i1 = new Intent(BookDetailsActivity.this, UploadReviewActivity.class);
            startActivity(i1);
        });

        manager = new LinearLayoutManager(BookDetailsActivity.this);
        recyclerView.setLayoutManager(manager);
        query = FirebaseDatabase.getInstance().getReference("book_reviews").orderByChild("bookId").equalTo(Review.getBookID());
        query.addListenerForSingleValueEvent(listener);
    }

    //Adding a ValueEventListener for library books reviews
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                Review review = dataSnapshot.getValue(Review.class);
                reviews.add(review);
            }

            reviewAdaptor = new ReviewAdaptor(reviews);
            recyclerView.setAdapter(reviewAdaptor);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
}