package com.example.studentapp;

public class Review {

    private String userID;
    private String userReview;
    private static String bookID;

    public Review(String userID, String userReview, String bookID) {
        this.userID = userID;
        this.userReview = userReview;
        this.bookID = bookID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserReview() {
        return userReview;
    }

    public void setUserReview(String userReview) {
        this.userReview = userReview;
    }

    public static String getBookID() {
        return bookID;
    }

    public static void setBookID(String bookID) {
        Review.bookID = bookID;
    }
}
