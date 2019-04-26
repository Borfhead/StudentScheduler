package com.example.studentscheduler;

import java.util.ArrayList;

enum Status{
    IN_PROGESS, COMPLETED, DROPPED, PLAN_TO_TAKE;

    public static Status getStatusFromString(String status){
        if(status == null){
            status = "PLAN TO TAKE";
        }
        Status toReturn = Status.IN_PROGESS;
        switch (status){
            case "PLAN TO TAKE":
                toReturn = Status.PLAN_TO_TAKE;
                break;
            case "IN PROGRESS":
                toReturn = Status.IN_PROGESS;
                break;
            case "DROPPED":
                toReturn = Status.DROPPED;
                break;
            case "COMPLETED":
                toReturn = Status.COMPLETED;
                break;
        }
        return toReturn;
    }


}

public class Course {

    private String title;
    private String start;
    private String end;
    public Status status;
    private String mentorName;
    private String mentorPhone;
    private String mentorEmail;
    private long id;

    public Course(){
        title = "";
        start = "";
        end = "";
        status = Status.DROPPED;
        mentorName = "";
        mentorPhone = "";
        mentorEmail = "";
        id = -1;

    }

    public Course(String title){
        this.title = title;
        start = "";
        end = "";
        status = Status.DROPPED;
        mentorName = "";
        mentorPhone = "";
        mentorEmail = "";
        id = -1;
    }

    public Course(String title, String start, String end, Status status,
                  String mentorName, String mentorPhone, String mentorEmail, long id){
        this.title = title;
        this.start = start;
        this.end = end;
        this.status = status;
        this.mentorName = mentorName;
        this.mentorPhone = mentorPhone;
        this.mentorEmail = mentorEmail;
        this.id = id;
    }

    public Course(String title, String start, String end, String status,
                  String mentorName, String mentorPhone, String mentorEmail, long id){
        this.title = title;
        this.start = start;
        this.end = end;
        this.mentorName = mentorName;
        this.mentorPhone = mentorPhone;
        this.mentorEmail = mentorEmail;
        this.status = Status.getStatusFromString(status);
        this.id = id;
    }
    @Override
    public String toString(){
        return this.title;
    }

    public String getTitle() {
        return title;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
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


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
