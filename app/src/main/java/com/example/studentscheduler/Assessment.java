package com.example.studentscheduler;

enum AssessmentType{
    OBJECTIVE, PERFORMANCE, NULL;

    public static AssessmentType getTypeFromString(String s){
        AssessmentType toReturn = AssessmentType.OBJECTIVE;
        switch (s){
            case "OBJECTIVE":
                toReturn = AssessmentType.OBJECTIVE;
                break;
            case "PERFORMANCE":
                toReturn = AssessmentType.PERFORMANCE;
                break;
        }
        return toReturn;
    }
}

public class Assessment {



    private String title;
    private String dueDate;
    public AssessmentType type;
    private long id;

    public Assessment(String title){
        this.title = title;
        dueDate = "";
        type = AssessmentType.NULL;
        id = -1;
    }

    public Assessment(String title, String dueDate, AssessmentType type, long id){
        this.title = title;
        this.dueDate = dueDate;
        this.type = type;
    }

    public Assessment(String title, String dueDate, String type, long id){
        this.title = title;
        this.dueDate = dueDate;
        this.id = id;
        this.type = AssessmentType.getTypeFromString(type);
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString(){
        if(type == AssessmentType.NULL){
            return title;
        }
        else{
            return type.name() + " ASSESSMENT:  " +title;
        }

    }
}
