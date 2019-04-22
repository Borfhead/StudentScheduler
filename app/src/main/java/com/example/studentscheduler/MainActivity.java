package com.example.studentscheduler;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.facebook.stetho.Stetho;

import java.time.LocalDate;


public class MainActivity extends AppCompatActivity {

    private static final int ADD_TERM_CODE = 1001;
    private static final int ADD_COURSE_CODE = 1002;
    private static final int ADD_ASSESSMENT_CODE = 1003;
    private static final int ADD_NOTE_CODE = 1004;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //For debugging
        Stetho.initializeWithDefaults(this);
    }

    public void addTerm(View view) {
        Intent intent = new Intent(this, AddTermActivity.class);
        startActivityForResult(intent, ADD_TERM_CODE);
    }

    public void addCourse(View view) {
        Intent intent = new Intent(this, AddCourseActivity.class);
        startActivityForResult(intent, ADD_COURSE_CODE);
    }

    public void addAssessment(View view) {
        Intent intent = new Intent(this, AddAssessmentActivity.class);
        startActivityForResult(intent, ADD_ASSESSMENT_CODE);
    }

    public void addNote(View view) {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivityForResult(intent, ADD_NOTE_CODE);
    }
}
