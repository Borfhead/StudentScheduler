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

    public static Uri insertNote(Context context, String text, long courseId){

        ContentValues values = new ContentValues();
        values.put(DBOpener.NOTE_TEXT, text);
        values.put(DBOpener.NOTE_COURSE_ID, courseId);
        return context.getContentResolver().insert(DBProvider.NOTE_URI, values);
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
