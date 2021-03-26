package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//This activity displays all books for sale in a RecyclerView
public class BooksForSaleActivity extends AppCompatActivity implements BookForSaleAdaptor.BookHolder.BookInterface {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    BookForSaleAdaptor bookForSaleAdaptor;

    DatabaseReference databaseReference;
    ArrayList<BookForSale> bookForSale = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_for_sale);

        recyclerView = findViewById(R.id.recyclerViewBookSale);
        databaseReference = FirebaseDatabase.getInstance().getReference("books_for_sale");
        layoutManager = new LinearLayoutManager(BooksForSaleActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        databaseReference.addListenerForSingleValueEvent(listener);

    }

    //Adding a ValueEventListener for books for sale
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                bookForSale.add(dataSnapshot.getValue(BookForSale.class));
            }
            bookForSaleAdaptor = new BookForSaleAdaptor(bookForSale,  BooksForSaleActivity.this);
            recyclerView.setAdapter(bookForSaleAdaptor);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    @Override
    public void onBookClick(int i) {
        Intent intent = new Intent(BooksForSaleActivity.this, BookForSaleDetailsActivity.class);
        intent.putExtra("Book", bookForSale.get(i));
        startActivity(intent);
    }
}