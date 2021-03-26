package com.example.studentapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//BookAdaptor that displays library books reviews
public class ReviewAdaptor extends RecyclerView.Adapter<ReviewAdaptor.ReviewHolder> {

    ArrayList<Review> review;

    public ReviewAdaptor(ArrayList<Review> _review) {this.review = _review;}

    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder (@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.review_card, viewGroup, false);
        return new ReviewHolder(view);

    }

    @Override
    public void onBindViewHolder (@NonNull ReviewHolder reviewHolder, int i) {
        reviewHolder.userID.setText(review.get(i).getUserName());
        reviewHolder.userReview.setText(review.get(i).getUserReview());
    }

    @Override
    public int getItemCount() {return review.size();}

    public static class ReviewHolder extends RecyclerView.ViewHolder {
        TextView userID, userReview;

        public ReviewHolder(@NonNull View itemView) {
            super(itemView);
            userID = itemView.findViewById(R.id.reviewUserID);
            userReview = itemView.findViewById(R.id.reviewContent);
        }

    }
}
