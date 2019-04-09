package com.example.studentscheduler;

import java.time.LocalDate;

enum AssessmentType{
    OBJECTIVE, PERFORMANCE
}

public class Assessment {

    private LocalDate dueDate;
    public AssessmentType type;

    public Assessment(LocalDate dueDate, AssessmentType type){
        this.dueDate = dueDate;
        this.type = type;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
