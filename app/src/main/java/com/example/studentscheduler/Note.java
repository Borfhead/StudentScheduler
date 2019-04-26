package com.example.studentscheduler;

public class Note {

    private String noteText;
    private long id;

    public Note(String noteText, long id){
        this.noteText = noteText;
        this.id = id;
    }

    public Note(String text){
        this.noteText = text;
        id = -1;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return noteText;
    }
}
