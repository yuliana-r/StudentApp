package com.example.studentapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewAdaptor extends RecyclerView.Adapter<ReviewAdaptor.ReviewHolder> {

    ArrayList<Review> reviews;

    public ReviewAdaptor(ArrayList<Review> _reviews) {this.reviews = _reviews;}

    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder (@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.review_card, viewGroup, false);
        return new ReviewHolder(view);

    }

    @Override
    public void onBindViewHolder (@NonNull ReviewHolder reviewHolder, int i) {
        reviewHolder.userID.setText(reviews.get(i).getUserID());
        reviewHolder.userReview.setText(reviews.get(i).getUserReview());
    }

    @Override
    public int getItemCount() {return reviews.size();}

    public static class ReviewHolder extends RecyclerView.ViewHolder {
        TextView userID, userReview;

        public ReviewHolder(@NonNull View itemView) {
            super(itemView);
            userID = itemView.findViewById(R.id.reviewUserID);
            userReview = itemView.findViewById(R.id.bookReview);
        }

    }
}
