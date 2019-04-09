package com.example.studentscheduler;

import java.time.LocalDate;
import java.util.ArrayList;

public class Term {

    private String title;
    private LocalDate start;
    private LocalDate end;
    private ArrayList<Course> courses;

    public Term(String title, LocalDate start, LocalDate end, ArrayList<Course> courses) {
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

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
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
