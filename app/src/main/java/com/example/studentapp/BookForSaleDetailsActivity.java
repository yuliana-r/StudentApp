package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class BookForSaleDetailsActivity extends AppCompatActivity {

    TextView bookSaleTitle, bookSaleAuthor, bookSaleISBN, bookSaleDesc;
    ImageView bookSaleImage;
    Button messageUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_for_sale_details);
        Intent i = getIntent();

        BookForSale bookForSale = i.getParcelableExtra("Book");
        bookSaleTitle = findViewById(R.id.bookForSaleDetailsTitle);
        bookSaleAuthor = findViewById(R.id.bookForSaleDetailsAuthor);
        bookSaleISBN = findViewById(R.id.bookForSaleDetailsISBN);
        bookSaleDesc = findViewById(R.id.bookForSaleDetailsDesc);
        bookSaleImage = findViewById(R.id.bookForSaleDetailsImage);
        messageUser = findViewById(R.id.messageUserButton);

        bookSaleTitle.setText(bookForSale.getBookTitle());
        bookSaleAuthor.setText(bookForSale.getBookAuthor());
        bookSaleISBN.setText(bookForSale.getBookISBN());
        bookSaleDesc.setText(bookForSale.getBookDescription());
        Picasso.get().load(bookForSale.getBookImgUrl()).fit().into(bookSaleImage);

        messageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //method to message user within the app or maybe email?
            }
        });

    }
}