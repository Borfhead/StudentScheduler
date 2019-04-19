package com.example.studentscheduler;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout test = findViewById(R.id.tabLayout);
        test.removeAllTabs();
        test.addTab(test.newTab().setText("Test 1"));
        test.addTab(test.newTab().setText("Test 2"));



//        ContentValues values = new ContentValues();
//        values.put(DBOpener.TERM_TITLE, "New Term");
//        values.put(DBOpener.TERM_START, "04/01/2019");
//        values.put(DBOpener.TERM_END, "05/01/2019");
//        Uri uri = getContentResolver().insert(DBProvider.TERM_URI, values);
    }
}
