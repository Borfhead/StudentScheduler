package com.example.studentscheduler;

enum AssessmentType{
    OBJECTIVE, PERFORMANCE
}

public class Assessment {



    private String title;
    private String dueDate;
    public AssessmentType type;

    public Assessment(String title, String dueDate, AssessmentType type){
        this.title = title;
        this.dueDate = dueDate;
        this.type = type;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
