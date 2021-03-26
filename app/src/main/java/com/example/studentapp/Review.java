package com.example.studentapp;

//This class is used to store information on library books reviews
public class Review {
    private String userName;
    private String userReview;
    private static String bookId;

    public Review(String bookId, String userName, String userReview) {
        this.bookId = bookId;
        this.userName = userName;
        this.userReview = userReview;

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
