package com.example.studentapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BookAdaptor extends RecyclerView.Adapter<BookAdaptor.BookHolder> {
    ArrayList<Book> books;
    BookHolder.BookInterface listener;

    public BookAdaptor(ArrayList<Book> _books, BookHolder.BookInterface _listener) {
        this.books = _books;
        listener = _listener;
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder (@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bookcard, viewGroup, false);
        return new BookHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder bookHolder, int i) {
        Picasso.get().load(books.get(i).getBookImgUrl()).fit().into(bookHolder.bookcardImage);
    }

    @Override
    public int getItemCount() {return books.size();}

    public static class BookHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        ImageView bookcardImage;
        TextView titleTv, authorTv, descTv;
        BookInterface listener;

        public BookHolder (@NonNull View itemView, BookInterface _listener) {
            super(itemView);
            bookcardImage = itemView.findViewById(R.id.bookcardIv);
            titleTv = itemView.findViewById(R.id.bookTitleTv);
            authorTv = itemView.findViewById(R.id.bookAuthorTv);
            descTv = itemView.findViewById(R.id.bookDescriptionTv);
            listener = _listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {listener.onBookClick(getAdapterPosition());}

        public interface BookInterface {
            public void onBookClick(int i);
        }


    }
}
