package com.example.studentscheduler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;

public class DBDriver {

    public static ArrayList<Term> allTerms = new ArrayList();

    public static Uri insertAssessment(Context context, String title, String dueDate,
                                       AssessmentType type, long courseId){
        ContentValues values = new ContentValues();
        values.put(DBOpener.ASSESSMENT_TITLE, title);
        values.put(DBOpener.ASSESSMENT_DUE_DATE, dueDate);
        values.put(DBOpener.ASSESSMENT_TYPE, type.name());
        values.put(DBOpener.ASSESSMENT_COURSE_ID, courseId);
        return context.getContentResolver().insert(DBProvider.ASSESSMENT_URI, values);
    }

    public static int updateAssessment(Context context, String title, String dueDate,
                                       AssessmentType type, long assessmentId){
        ContentValues values = new ContentValues();
        values.put(DBOpener.ASSESSMENT_TITLE, title);
        values.put(DBOpener.ASSESSMENT_DUE_DATE, dueDate);
        values.put(DBOpener.ASSESSMENT_TYPE, type.name());
        return context.getContentResolver().update(DBProvider.ASSESSMENT_URI, values,
                DBOpener.ASSESSMENT_ID + " = " + assessmentId, null);
    }

    public static ArrayList<Assessment> getAssessmentByCourse(Context context ,long courseId){
        ArrayList<Assessment> toReturn = new ArrayList<>();
        Cursor cursor;
        cursor = context.getContentResolver().query(DBProvider.ASSESSMENT_URI, DBOpener.ASSESSMENT_COLUMNS,
                DBOpener.ASSESSMENT_COURSE_ID + " = " +courseId, null, "ASC");
        try {
            while (cursor.moveToNext()) {
                String title = cursor.getString(cursor.getColumnIndex(DBOpener.ASSESSMENT_TITLE));
                String dueDate = cursor.getString(cursor.getColumnIndex(DBOpener.ASSESSMENT_DUE_DATE));
                String type = cursor.getString(cursor.getColumnIndex(DBOpener.ASSESSMENT_TYPE));
                long id = cursor.getLong(cursor.getColumnIndex(DBOpener.ASSESSMENT_ID));
                Assessment toAdd = new Assessment(title, dueDate, type, id);
                toReturn.add(toAdd);
            }
        }
        finally{
            cursor.close();
        }
        return toReturn;
    }

    public static Uri insertCourse(Context context, String title, String start, String end,
                                   Status status, String mentorName, String mentorPhone,
                                   String mentorEmail, long termId){
        ContentValues values = new ContentValues();
        values.put(DBOpener.COURSE_TITLE, title);
        values.put(DBOpener.COURSE_START, start);
        values.put(DBOpener.COURSE_END, end);
        values.put(DBOpener.COURSE_STATUS, status.name());
        values.put(DBOpener.COURSE_MENTOR_NAME, mentorName);
        values.put(DBOpener.COURSE_MENTOR_PHONE, mentorPhone);
        values.put(DBOpener.COURSE_MENTOR_EMAIL, mentorEmail);
        values.put(DBOpener.COURSE_TERM_ID, termId);
        return context.getContentResolver().insert(DBProvider.COURSE_URI, values);
    }

    public static ArrayList<Course> getCoursesByTerm(Context context, long termId){
        ArrayList<Course> toReturn = new ArrayList();
        Cursor cursor;
        cursor = context.getContentResolver().query(DBProvider.COURSE_URI, DBOpener.COURSE_COLUMNS,
                DBOpener.COURSE_TERM_ID + " = " +termId, null, "ASC");
        try {
            while (cursor.moveToNext()) {
                String title = cursor.getString(cursor.getColumnIndex(DBOpener.COURSE_TITLE));
                String start = cursor.getString(cursor.getColumnIndex(DBOpener.COURSE_START));
                String end = cursor.getString(cursor.getColumnIndex(DBOpener.COURSE_END));
                long courseId = cursor.getLong(cursor.getColumnIndex(DBOpener.COURSE_ID));
                String statusString = cursor.getString(cursor.getColumnIndex(DBOpener.COURSE_STATUS));
                String mentorName = cursor.getString(cursor.getColumnIndex(DBOpener.COURSE_MENTOR_NAME));
                String mentorEmail = cursor.getString(cursor.getColumnIndex(DBOpener.COURSE_MENTOR_EMAIL));
                String mentorPhone = cursor.getString(cursor.getColumnIndex(DBOpener.COURSE_MENTOR_PHONE));
                Course toAdd = new Course(title, start, end, statusString, mentorName, mentorPhone, mentorEmail, courseId);
                toReturn.add(toAdd);
            }
        }
        finally{
            cursor.close();
        }
        return toReturn;
    }

    public static Course getCourse(Context context, long id){
        Course toReturn = new Course();
        Cursor cursor;
        cursor = context.getContentResolver().query(DBProvider.COURSE_URI, DBOpener.COURSE_COLUMNS,
                DBOpener.COURSE_ID + " = " + id, null, "ASC");
        if(cursor.getCount() >= 1){
            cursor.moveToFirst();
            String title = cursor.getString(cursor.getColumnIndex(DBOpener.COURSE_TITLE));
            String start = cursor.getString(cursor.getColumnIndex(DBOpener.COURSE_START));
            String end = cursor.getString(cursor.getColumnIndex(DBOpener.COURSE_END));
            String status = cursor.getString(cursor.getColumnIndex(DBOpener.COURSE_STATUS));
            String mentorName = cursor.getString(cursor.getColumnIndex(DBOpener.COURSE_MENTOR_NAME));
            String mentorEmail = cursor.getString(cursor.getColumnIndex(DBOpener.COURSE_MENTOR_EMAIL));
            String mentorPhone = cursor.getString(cursor.getColumnIndex(DBOpener.COURSE_MENTOR_PHONE));
            toReturn = new Course(title, start, end, status, mentorName, mentorPhone, mentorEmail, id);
        }
        return toReturn;
    }

    public static int updateCourse(Context context, long courseId, String title, String start, String end,
                                   Status status, String mentorName, String mentorPhone,
                                   String mentorEmail){
        ContentValues values = new ContentValues();
        values.put(DBOpener.COURSE_TITLE, title);
        values.put(DBOpener.COURSE_START, start);
        values.put(DBOpener.COURSE_END, end);
        values.put(DBOpener.COURSE_STATUS, status.name());
        values.put(DBOpener.COURSE_MENTOR_NAME, mentorName);
        values.put(DBOpener.COURSE_MENTOR_PHONE, mentorPhone);
        values.put(DBOpener.COURSE_MENTOR_EMAIL, mentorEmail);
        return context.getContentResolver().update(DBProvider.COURSE_URI, values,
                DBOpener.COURSE_ID + " = " + courseId, null);
    }

    public static Uri insertNote(Context context, String text, long courseId){

        ContentValues values = new ContentValues();
        values.put(DBOpener.NOTE_TEXT, text);
        values.put(DBOpener.NOTE_COURSE_ID, courseId);
        return context.getContentResolver().insert(DBProvider.NOTE_URI, values);
    }

    public static int updateNote(Context context, String text, long noteId){
        ContentValues values = new ContentValues();
        values.put(DBOpener.NOTE_TEXT, text);
        return context.getContentResolver().update(DBProvider.NOTE_URI, values,
                DBOpener.NOTE_ID + " = " + noteId, null);
    }

    public static ArrayList<Note> getNotesByCourse(Context context, long courseId){
        ArrayList<Note> toReturn = new ArrayList();
        Cursor cursor;
        cursor = context.getContentResolver().query(DBProvider.NOTE_URI, DBOpener.NOTE_COLUMNS,
                DBOpener.NOTE_COURSE_ID + " = " +courseId, null, "ASC");
        try {
            while (cursor.moveToNext()) {
                String text = cursor.getString(cursor.getColumnIndex(DBOpener.NOTE_TEXT));
                long id = cursor.getLong(cursor.getColumnIndex(DBOpener.NOTE_ID));
                Note toAdd = new Note(text, id);
                toReturn.add(toAdd);
            }
        }
        finally{
            cursor.close();
        }
        return toReturn;
    }

    public static Uri insertTerm(Context context, String title, String start, String end){
        ContentValues values = new ContentValues();
        values.put(DBOpener.TERM_TITLE, title);
        values.put(DBOpener.TERM_START, start);
        values.put(DBOpener.TERM_END, end);
        return context.getContentResolver().insert(DBProvider.TERM_URI, values);
    }

    public static Term getTerm(Context context, long id){
        Term toReturn = new Term();
        Cursor cursor;
        cursor = context.getContentResolver().query(DBProvider.TERM_URI, DBOpener.TERM_COLUMNS,
                DBOpener.TERM_ID + " = " + id, null, "ASC");
        if(cursor.getCount() >= 1){
            cursor.moveToFirst();
            String title = cursor.getString(cursor.getColumnIndex(DBOpener.TERM_TITLE));
            String start = cursor.getString(cursor.getColumnIndex(DBOpener.TERM_START));
            String end = cursor.getString(cursor.getColumnIndex(DBOpener.TERM_END));
            toReturn = new Term(title, start, end, id);
        }
        return toReturn;
    }

    public static int updateTerm(Context context, String title, String start, String end, long id){
        ContentValues values = new ContentValues();
        values.put(DBOpener.TERM_TITLE, title);
        values.put(DBOpener.TERM_START, start);
        values.put(DBOpener.TERM_END, end);
        return context.getContentResolver().update(DBProvider.TERM_URI, values,
                DBOpener.TERM_ID + " = " +id, null);
    }

    public static void populateAllTermsList(Context context){
        allTerms.clear();
        Cursor cursor;
        cursor = context.getContentResolver().query(DBProvider.TERM_URI, DBOpener.TERM_COLUMNS,
                null, null, "ASC");

        try {
            while (cursor.moveToNext()) {
                String title = cursor.getString(cursor.getColumnIndex(DBOpener.TERM_TITLE));
                String start = cursor.getString(cursor.getColumnIndex(DBOpener.TERM_START));
                String end = cursor.getString(cursor.getColumnIndex(DBOpener.TERM_END));
                long id = cursor.getLong(cursor.getColumnIndex(DBOpener.TERM_ID));
                Term toAdd = new Term(title, start, end, id);
                allTerms.add(toAdd);
            }
        }
        finally{
            cursor.close();
        }
    }

}
