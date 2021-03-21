package com.example.studentapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookForSaleAdaptor extends RecyclerView.Adapter<BookForSaleAdaptor.BookHolder> {
    ArrayList<BookForSale> bookForSale;
    BookHolder.BookInterface listener;

    public BookForSaleAdaptor(ArrayList<BookForSale> _bookForSale, BookHolder.BookInterface _listener) {
        this.bookForSale = _bookForSale;
        listener = _listener;
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder (@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.book_for_sale_card, viewGroup, false);
        return new BookHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder bookHolder, int i) {
        Picasso.get().load(bookForSale.get(i).getBookImgUrl()).fit().into(bookHolder.bookcardImage);
        bookHolder.titleTv.setText(bookForSale.get(i).getBookTitle());
        bookHolder.authorTv.setText(bookForSale.get(i).getBookAuthor());
        bookHolder.descTv.setText(bookForSale.get(i).getBookDescription());
    }

    @Override
    public int getItemCount() {return bookForSale.size();}

    public static class BookHolder extends RecyclerView.ViewHolder implements  View.OnClickListener {
        ImageView bookcardImage;
        TextView titleTv, authorTv, descTv;
        BookInterface listener;

        public BookHolder (@NonNull View itemView, BookInterface _listener) {
            super(itemView);
            bookcardImage = itemView.findViewById(R.id.bookForSaleIv);
            titleTv = itemView.findViewById(R.id.bookForSaleTitleTv);
            authorTv = itemView.findViewById(R.id.bookForSaleAuthorTv);
            descTv = itemView.findViewById(R.id.bookForSaleDescriptionTv);
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
