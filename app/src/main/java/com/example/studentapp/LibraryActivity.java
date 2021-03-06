package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//This activity allows the user to browse book categories, view and upload books for sale
public class LibraryActivity extends AppCompatActivity implements BookAdaptor.BookHolder.BookInterface {

    Button uploadBook;
    Button viewBookSale, businessBooks, computingBooks, economicsBooks, mathBooks;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    BookAdaptor bookAdaptor;

    DatabaseReference databaseReference;
    ArrayList<Book> book = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        businessBooks = findViewById(R.id.businessBooksButton);
        computingBooks = findViewById(R.id.computingBooksButton);
        economicsBooks = findViewById(R.id.economicsBooksButton);
        mathBooks = findViewById(R.id.mathBooksButton);

        recyclerView = findViewById(R.id.recyclerViewLibrary);
        layoutManager = new LinearLayoutManager(LibraryActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        viewBookSale = findViewById(R.id.viewBooksForSaleButton);
        uploadBook = findViewById(R.id.uploadBookForSaleButton);


        viewBookSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LibraryActivity.this, BooksForSaleActivity.class);
                startActivity(i);
            }
        });

        uploadBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LibraryActivity.this, UploadBookForSaleActivity.class);
                startActivity(i);
            }
        });

        businessBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LibraryActivity.this, LibraryCategoryActivity.class);
                intent.putExtra("category", "Business");
                startActivity(intent);
            }
        });

        computingBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LibraryActivity.this, LibraryCategoryActivity.class);
                intent.putExtra("category", "Computing");
                startActivity(intent);
            }
        });

        economicsBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LibraryActivity.this, LibraryCategoryActivity.class);
                intent.putExtra("category", "Economics");
                startActivity(intent);
            }
        });

        mathBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LibraryActivity.this, LibraryCategoryActivity.class);
                intent.putExtra("category", "Mathematics");
                startActivity(intent);
            }
        });

       databaseReference = FirebaseDatabase.getInstance().getReference("books_in_library");
       databaseReference.addListenerForSingleValueEvent(listener);
    }

    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                book.add(dataSnapshot.getValue(Book.class));
            }
            bookAdaptor = new BookAdaptor(book, LibraryActivity.this);
            recyclerView.setAdapter(bookAdaptor);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    @Override
    public void onBookClick(int i) {
        Intent intent = new Intent(LibraryActivity.this, BookDetailsActivity.class);
        intent.putExtra("Book", book.get(i));
        startActivity(intent);
    }




}