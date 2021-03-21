package com.example.studentapp;

public class Review {

    private String userID;
    private String userReview;
    private static String bookId;

    public Review(String userID, String userReview, String bookId) {
        this.userID = userID;
        this.userReview = userReview;
        this.bookId = bookId;
    }

    public Review() {}

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
        return bookId;
    }

    public static void setBookID(String bookID) {
        Review.bookId = bookID;
    }
}
