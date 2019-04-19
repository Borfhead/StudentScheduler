package com.example.studentscheduler;


import java.util.ArrayList;

public class Term {

    private String title;
    private String start;
    private String end;
    private ArrayList<Course> courses;

    public Term(String title, String start, String end, ArrayList<Course> courses) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.courses = courses;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course toAdd){
        courses.add(toAdd);
    }

}
