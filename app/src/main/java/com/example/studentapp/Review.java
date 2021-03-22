package com.example.studentapp;

public class Review {
    private String userName;
    private String userReview;
    private static String bookId;

    public Review(String userName, String userReview, String bookId) {
        this.userName = userName;
        this.userReview = userReview;
        this.bookId = bookId;
    }

    public Review() {}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
