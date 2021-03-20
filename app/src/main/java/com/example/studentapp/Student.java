package com.example.studentapp;

public class Student {
    public String studentEmail;
    public String studentPassword;
    public String studentFirstName;
    public String studentLastName;
    public String studentID;

    public Student(String studentEmail, String studentPassword, String studentFirstName, String studentLastName, String studentID) {
        this.studentEmail = studentEmail;
        this.studentPassword = studentPassword;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.studentID = studentID;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
}
