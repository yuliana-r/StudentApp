package com.example.studentapp.model;

public class StudentLoginDetail {
    private String firstName;
    private String surname;
    private String studentID;

    public StudentLoginDetail() {
    }

    public StudentLoginDetail(String firstName, String surname, String studentID) {
        this.firstName = firstName;
        this.surname = surname;
        this.studentID = studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
}
