package com.example.studentapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private String bookId, bookTitle, bookAuthor, bookISBN, bookDescription, bookImgUrl, bookCategory;
    boolean canReserve;

    public Book(String bookId, String bookTitle, String bookAuthor, String bookISBN, String bookDescription, String bookImgUrl, String bookCategory, boolean canReserve) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookISBN = bookISBN;
        this.bookDescription = bookDescription;
        this.bookImgUrl = bookImgUrl;
        this.bookCategory = bookCategory;
        this.canReserve = canReserve;
    }

    public Book() {}


    protected  Book (Parcel in) {
        bookId = in.readString();
        bookTitle = in.readString();
        bookAuthor = in.readString();
        bookISBN = in.readString();
        bookDescription = in.readString();
        bookImgUrl = in.readString();
        bookCategory = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getBookImgUrl() {
        return bookImgUrl;
    }

    public void setBookImgUrl(String bookImgUrl) {
        this.bookImgUrl = bookImgUrl;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(bookId);
        dest.writeString(bookTitle);
        dest.writeString(bookAuthor);
        dest.writeString(bookISBN);
        dest.writeString(bookDescription);
        dest.writeString(bookImgUrl);
        dest.writeString(bookCategory);
    }
}
