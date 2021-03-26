package com.example.studentapp;

//This class is used to store information about forum posts
public class ForumPost {
    private  String title, userID, postedDate, postContent, threadID;

    public ForumPost(String title, String userID, String postedDate, String postContent, String threadID) {
        this.title = title;
        this.userID = userID;
        this.postedDate = postedDate;
        this.postContent = postContent;
        this.threadID = threadID;
    }

    public ForumPost() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getThreadID() {
        return threadID;
    }

    public void setThreadID(String threadID) {
        this.threadID = threadID;
    }

}
