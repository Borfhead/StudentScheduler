package com.example.studentscheduler;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddCourseActivity extends AppCompatActivity {

    Receiver receiver;
    TextView titleField;
    TextView startField;
    TextView endField;
    TextView mentorNameField;
    TextView mentorPhoneField;
    TextView mentorEmailField;
    final Calendar calendar = Calendar.getInstance();
    int field = 1;
    Spinner statusSpinner;
    static int requestCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        setTitle("Add Course");

        receiver = new Receiver();

        titleField = findViewById(R.id.titleField);
        startField = findViewById(R.id.startField);
        endField = findViewById(R.id.endField);
        startField.setKeyListener(null);
        endField.setKeyListener(null);
        mentorNameField = findViewById(R.id.mentorNameField);
        mentorPhoneField = findViewById(R.id.mentorPhoneField);
        mentorEmailField = findViewById(R.id.mentorEmailField);

        statusSpinner = findViewById(R.id.statusSpinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.statusArray, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(arrayAdapter);

        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                if(field == 1){
                    populateField(startField);
                }
                if(field == 2){
                    populateField(endField);
                }
            }
        };

        startField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                field = 1;
                new DatePickerDialog(AddCourseActivity.this, dateSetListener, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        endField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                field = 2;
                new DatePickerDialog(AddCourseActivity.this, dateSetListener, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    private void populateField(TextView toPopulate){
        String format = "MM/dd/yyyy";
        SimpleDateFormat simpleFormat = new SimpleDateFormat(format, Locale.getDefault());
        toPopulate.setText(simpleFormat.format(calendar.getTime()));
    }

    public void addBtnClicked(View view) {
        Date startDate = null;
        Date endDate = null;
        Status courseStatus = Status.PLAN_TO_TAKE;
        String title = titleField.getText().toString();
        String start = startField.getText().toString();
        String end = endField.getText().toString();
        String mentorName = mentorNameField.getText().toString();
        String mentorPhone = mentorPhoneField.getText().toString();
        String mentorEmail = mentorEmailField.getText().toString();
        long id = getIntent().getLongExtra("TERM_ID", 0);
        switch (statusSpinner.getSelectedItemPosition()){
            case 0:
                courseStatus = Status.PLAN_TO_TAKE;
                break;
            case 1:
                courseStatus = Status.IN_PROGESS;
                break;
            case 2:
                courseStatus = Status.COMPLETED;
                break;
            case 3:
                courseStatus = Status.DROPPED;
                break;
        }
        DBDriver.insertCourse(this, title, start, end, courseStatus, mentorName, mentorPhone,
                mentorEmail, id);
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        try{
            startDate = format.parse(start);
            setNotification(startDate.getTime(), "COURSE_START");
        } catch(Exception e){
            System.out.println("Couldn't parse date");
        }
        try{
            endDate = format.parse(end);
            setNotification(endDate.getTime(), "COURSE_END");
        } catch(Exception e){
            System.out.println("Couldn't parse date");
        }
        finish();
    }

    @Override
    protected void onStop(){
        super.onStop();
        try{
            unregisterReceiver(receiver);
        }catch(IllegalArgumentException e){
            System.out.println("Couldn't unregister receiver");
        }
    }

    private void setNotification(long millis, String notificationType){
        IntentFilter filter = new IntentFilter("studentscheduler.action");
        registerReceiver(receiver, filter);
        AlarmManager alarms = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(AddCourseActivity.this, Receiver.class);
        intent.putExtra("NOTIFICATION_TYPE", notificationType);
        PendingIntent pendingStart = PendingIntent.getBroadcast(this, requestCode++, intent, 0);
        alarms.set(AlarmManager.RTC_WAKEUP, millis, pendingStart);
    }
}
