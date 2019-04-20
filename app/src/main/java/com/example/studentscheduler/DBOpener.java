package com.example.studentscheduler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLOutput;

public class DBOpener extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "scheduler.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TERM_TABLE = "term";
    public static final String TERM_ID = "_id";
    public static final String TERM_TITLE = "title";
    public static final String TERM_START = "term_start";
    public static final String TERM_END = "term_end";
    public static final String[] TERM_COLUMNS = {TERM_ID, TERM_TITLE, TERM_START, TERM_END};

    public static final String COURSE_TABLE = "course";
    public static final String COURSE_ID = "_id";
    public static final String COURSE_TITLE = "title";
    public static final String COURSE_START = "course_start";
    public static final String COURSE_END = "course_end";
    public static final String COURSE_STATUS = "status";
    public static final String COURSE_MENTOR_NAME = "mentor_name";
    public static final String COURSE_MENTOR_PHONE = "mentor_phone";
    public static final String COURSE_MENTOR_EMAIL = "mentor_email";
    public static final String COURSE_TERM_ID = "term_id";
    public static final String[] COURSE_COLUMNS = {COURSE_ID, COURSE_TITLE, COURSE_START,
            COURSE_END, COURSE_STATUS, COURSE_MENTOR_NAME,
            COURSE_MENTOR_PHONE, COURSE_MENTOR_EMAIL, COURSE_TERM_ID};

    public static final String NOTE_TABLE = "notes";
    public static final String NOTE_ID = "_id";
    public static final String NOTE_TEXT = "text";
    public static final String NOTE_COURSE_ID = "course_id";
    public static final String[] NOTE_COLUMNS = {NOTE_ID, NOTE_TEXT, NOTE_COURSE_ID};

    public static final String ASSESSMENT_TABLE = "assessment";
    public static final String ASSESSMENT_ID = "_id";
    public static final String ASSESSMENT_TITLE = "title";
    public static final String ASSESSMENT_DUE_DATE = "due_date";
    public static final String ASSESSMENT_TYPE = "type";
public static final String ASSESSMENT_COURSE_ID = "course_id";
    public static final String[] ASSESSMENT_COLUMNS = {ASSESSMENT_ID,
            ASSESSMENT_TITLE, ASSESSMENT_DUE_DATE, ASSESSMENT_TYPE, ASSESSMENT_COURSE_ID};


    private static final String CREATE_TERM_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TERM_TABLE + " (" +
                    TERM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TERM_TITLE + " TEXT, " +
                    TERM_START + " DATE, " +
                    TERM_END + " DATE)";

    private static final String CREATE_COURSE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + COURSE_TABLE + " (" +
                    COURSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COURSE_TITLE + " TEXT, " +
                    COURSE_START + " DATE, " +
                    COURSE_END + " DATE, " +
                    COURSE_STATUS + " TEXT, " +
                    COURSE_MENTOR_NAME + " TEXT, " +
                    COURSE_MENTOR_PHONE + " TEXT, " +
                    COURSE_MENTOR_EMAIL + " TEXT, " +
                    COURSE_TERM_ID + " INTEGER, " +
                    "FOREIGN KEY(" + COURSE_TERM_ID + ") REFERENCES " + TERM_TABLE + "(" +TERM_ID+ "))";

    private static final String CREATE_NOTE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + NOTE_TABLE + " (" +
                    NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NOTE_TEXT + " TEXT, " +
                    NOTE_COURSE_ID + " INTEGER, " +
                    "FOREIGN KEY(" + NOTE_COURSE_ID + ") REFERENCES " + COURSE_TABLE + "(" +COURSE_ID+ "))";

    private static final String CREATE_ASSESSMENT_TABLE =
            "CREATE TABLE IF NOT EXISTS " + ASSESSMENT_TABLE + "(" +
                    ASSESSMENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    ASSESSMENT_TITLE + " TEXT, " +
                    ASSESSMENT_DUE_DATE + " DATE, " +
                    ASSESSMENT_TYPE + " TEXT, " +
                    ASSESSMENT_COURSE_ID + " INTEGER, " +
                    "FOREIGN KEY(" +ASSESSMENT_COURSE_ID+ ") REFERENCES " +COURSE_TABLE+ "(" +COURSE_ID+ "))";

    public DBOpener(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TERM_TABLE);
        db.execSQL(CREATE_COURSE_TABLE);
        db.execSQL(CREATE_ASSESSMENT_TABLE);
        db.execSQL(CREATE_NOTE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TERM_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + COURSE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ASSESSMENT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + NOTE_TABLE);
        onCreate(db);
    }
}
