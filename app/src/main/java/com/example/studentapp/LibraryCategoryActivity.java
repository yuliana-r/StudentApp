package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//Activity to help user navigate through library book categories
public class LibraryCategoryActivity extends AppCompatActivity implements  BookAdaptor.BookHolder.BookInterface {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    BookAdaptor bookAdaptor;
    TextView bookCategory;

    Query databaseReference;
    ArrayList<Book> books = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_category);

        recyclerView = findViewById(R.id.recyclerView);
        bookCategory = findViewById(R.id.bookCategoryTv);
        layoutManager = new LinearLayoutManager(LibraryCategoryActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        Intent i = getIntent();
        String category = i.getStringExtra("category");
        bookCategory.setText(category);

        //Display books from "books_in_library" node for a certain category
        databaseReference = FirebaseDatabase.getInstance().getReference("books_in_library").orderByChild("bookCategory").equalTo(category);
        databaseReference.addListenerForSingleValueEvent(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                books.add(dataSnapshot.getValue(Book.class));
            }
            bookAdaptor = new BookAdaptor(books, LibraryCategoryActivity.this);
            recyclerView.setAdapter(bookAdaptor);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {}
    };

    public void onBookClick(int i) {
        Intent intent = new Intent(LibraryCategoryActivity.this, BookDetailsActivity.class);
        intent.putExtra("Book", books.get(i));
        startActivity(intent);
    }
}