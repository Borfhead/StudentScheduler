package com.example.studentscheduler;


import java.util.ArrayList;

public class Term {

    private String title;
    private String start;
    private String end;
    private  long id;

    public Term(){
        title = "";
        start = "";
        end = "";
    }

    public Term(String title, String start, String end, long id) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.id = id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
