package com.example.studentscheduler;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class DBProvider extends ContentProvider {

    private static final String AUTHORITY = "com.example.studentscheduler.dbprovider";
    private static final String ASSESSMENT_PATH = "assessment";
    private static final String COURSE_PATH = "course";
    private static final String NOTE_PATH = "note";
    private static final String TERM_PATH = "term";

    public static final Uri ASSESSMENT_URI =
            Uri.parse("content://" + AUTHORITY + "/" + ASSESSMENT_PATH);
    public static final Uri COURSE_URI =
            Uri.parse("content://" + AUTHORITY + "/" + COURSE_PATH);
    public static final Uri NOTE_URI =
            Uri.parse("content://" + AUTHORITY + "/" + NOTE_PATH);
    public static final Uri TERM_URI =
            Uri.parse("content://" + AUTHORITY + "/" + TERM_PATH);

    private static final int ASSESSMENT = 1;
    private static final int ASSESSMENT_ID = 2;
    private static final int COURSE = 3;
    private static final int COURSE_ID = 4;
    private static final int NOTE = 5;
    private static final int NOTE_ID = 6;
    private static final int TERM = 7;
    private static final int TERM_ID = 8;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static{
        uriMatcher.addURI(AUTHORITY, ASSESSMENT_PATH, ASSESSMENT);
        uriMatcher.addURI(AUTHORITY, ASSESSMENT_PATH + "/#", ASSESSMENT_ID);
        uriMatcher.addURI(AUTHORITY, COURSE_PATH, COURSE);
        uriMatcher.addURI(AUTHORITY, COURSE_PATH + "/#", COURSE_ID);
        uriMatcher.addURI(AUTHORITY, NOTE_PATH, NOTE);
        uriMatcher.addURI(AUTHORITY, NOTE_PATH + "/#", NOTE_ID);
        uriMatcher.addURI(AUTHORITY, TERM_PATH, TERM);
        uriMatcher.addURI(AUTHORITY, TERM_PATH + "/#", TERM_ID);
    }


    private SQLiteDatabase database;
    @Override
    public boolean onCreate() {
        DBOpener opener = new DBOpener(getContext());
        database = opener.getWritableDatabase();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[]
            selectionArgs, String sortOrder) {
        Cursor toReturn = null;
        switch(uriMatcher.match(uri)){
            case ASSESSMENT:
                toReturn = database.query(DBOpener.ASSESSMENT_TABLE, DBOpener.ASSESSMENT_COLUMNS, selection,
                        null, null, null, DBOpener.ASSESSMENT_ID + " " + sortOrder);
                break;
            case ASSESSMENT_ID:
                toReturn = database.query(DBOpener.ASSESSMENT_TABLE, DBOpener.ASSESSMENT_COLUMNS, selection,
                        null, null, null, DBOpener.ASSESSMENT_ID + " " + sortOrder);
                break;
            case COURSE:
                toReturn = database.query(DBOpener.COURSE_TABLE, DBOpener.COURSE_COLUMNS, selection,
                        null, null, null, DBOpener.COURSE_ID + " " + sortOrder);
                break;
            case COURSE_ID:
                toReturn = database.query(DBOpener.COURSE_TABLE, DBOpener.COURSE_COLUMNS, selection,
                        null, null, null, DBOpener.COURSE_ID + " " + sortOrder);
                break;
            case NOTE:
                toReturn = database.query(DBOpener.NOTE_TABLE, DBOpener.NOTE_COLUMNS, selection,
                        null, null, null, DBOpener.NOTE_ID + " " + sortOrder);
                break;
            case NOTE_ID:
                toReturn = database.query(DBOpener.NOTE_TABLE, DBOpener.NOTE_COLUMNS, selection,
                        null, null, null, DBOpener.NOTE_ID + " " + sortOrder);
                break;
            case TERM:
                toReturn = database.query(DBOpener.TERM_TABLE, DBOpener.TERM_COLUMNS, selection,
                        null, null, null, DBOpener.TERM_START + " " + sortOrder);
                break;
            case TERM_ID:
                toReturn = database.query(DBOpener.TERM_TABLE, DBOpener.TERM_COLUMNS, selection,
                        null, null, null, DBOpener.TERM_ID + " " + sortOrder);
        }
        return toReturn;
    }


    @Override
    public String getType(Uri uri) {
        return null;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id;
        Uri toReturn = null;
        switch(uriMatcher.match(uri)){
            case ASSESSMENT:
                id = database.insert(DBOpener.ASSESSMENT_TABLE, null, values);
                return Uri.parse(ASSESSMENT_PATH + "/" +id);
            case COURSE:
                id = database.insert(DBOpener.COURSE_TABLE, null, values);
                return Uri.parse(COURSE_PATH + "/" +id);
            case NOTE:
                id = database.insert(DBOpener.NOTE_TABLE, null, values);
                return Uri.parse(NOTE_PATH + "/" +id);
            case TERM:
                id = database.insert(DBOpener.TERM_TABLE, null, values);
                return Uri.parse(TERM_PATH + "/" +id);
        }
        return toReturn;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        switch(uriMatcher.match(uri)){
            case ASSESSMENT:
                return database.delete(DBOpener.ASSESSMENT_TABLE, selection, selectionArgs);
            case COURSE:
                return database.delete(DBOpener.COURSE_TABLE, selection, selectionArgs);
            case NOTE:
                return database.delete(DBOpener.NOTE_TABLE, selection, selectionArgs);
            case TERM:
                return database.delete(DBOpener.TERM_TABLE, selection, selectionArgs);
        }
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        switch(uriMatcher.match(uri)){
            case ASSESSMENT:
                return database.update(DBOpener.ASSESSMENT_TABLE, values, selection, selectionArgs);
            case COURSE:
                return database.update(DBOpener.COURSE_TABLE, values, selection, selectionArgs);
            case NOTE:
                return database.update(DBOpener.NOTE_TABLE, values, selection, selectionArgs);
            case TERM:
                return database.update(DBOpener.TERM_TABLE, values, selection, selectionArgs);
        }
        return 0;
    }
}
