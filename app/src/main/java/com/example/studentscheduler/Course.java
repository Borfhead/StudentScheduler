package com.example.studentscheduler;

import java.time.LocalDate;
import java.util.ArrayList;

enum Status{
    IN_PROGESS, COMPLETED, DROPPED, PLAN_TO_TAKE
}

public class Course {

    private String title;

    private LocalDate start;
    private LocalDate end;
    public Status status;
    private String mentorName;
    private String mentorPhone;
    private String mentorEmail;
    private ArrayList<String> notes;
    private ArrayList<Assessment> assessments;


    public Course(String title, LocalDate start, LocalDate end, Status status,
    String mentorName, String mentorPhone, String mentorEmail, ArrayList<String> notes, ArrayList<Assessment> assessments){
        this.title = title;
        this.start = start;
        this.end = end;
        this.status = status;
        this.mentorName = mentorName;
        this.mentorPhone = mentorPhone;
        this.mentorEmail = mentorEmail;
        this.notes = notes;
        this.assessments = assessments;
    }


    public String getTitle() {
        return title;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public Status getStatus() {
        return status;
    }

    public String getMentorName() {
        return mentorName;
    }

    public String getMentorPhone() {
        return mentorPhone;
    }

    public String getMentorEmail() {
        return mentorEmail;
    }

    public ArrayList<String> getNotes() {
        return notes;
    }

    public ArrayList<Assessment> getAssessments() {
        return assessments;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public void setMentorPhone(String mentorPhone) {
        this.mentorPhone = mentorPhone;
    }

    public void setMentorEmail(String mentorEmail) {
        this.mentorEmail = mentorEmail;
    }

    public void setNotes(ArrayList<String> notes) {
        this.notes = notes;
    }

    public void setAssessments(ArrayList<Assessment> assessments) {
        this.assessments = assessments;
    }

    public void addAssessment(Assessment toAdd){
        assessments.add(toAdd);
    }


}
