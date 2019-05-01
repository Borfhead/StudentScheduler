package com.example.studentscheduler;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//import com.facebook.stetho.Stetho;


public class MainActivity extends AppCompatActivity {

    public static final int ADD_TERM_CODE = 1001;
    public static final int ADD_COURSE_CODE = 1002;
    public static final int ADD_ASSESSMENT_CODE = 1003;
    public static final int ADD_NOTE_CODE = 1004;
    public static final int VIEW_COURSE_CODE = 1005;
    public static final int EDIT_TERM_CODE = 1006;
    public static final int EDIT_COURSE_CODE = 1007;
    public static final int EDIT_NOTE_CODE = 1008;
    public static final int EDIT_ASSESSMENT_CODE = 1009;

    private ViewPager pager;
    TermCollectionPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupActivity();


//        For debugging
//        Stetho.initializeWithDefaults(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        setupActivity();
    }

    private void setupActivity(){
        DBDriver.populateAllTermsList(this);
        adapter = new TermCollectionPagerAdapter(this, getSupportFragmentManager());
        pager = findViewById(R.id.termPager);
        pager.setAdapter(adapter);
        TabLayout tabLayout = findViewById(R.id.termTabLayout);
        tabLayout.setupWithViewPager(pager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        setTitle("Schedule");
    }

}