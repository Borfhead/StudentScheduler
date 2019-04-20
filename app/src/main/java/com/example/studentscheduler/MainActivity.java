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
}
