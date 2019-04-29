package com.example.studentscheduler;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditCourseActivity extends AppCompatActivity {

    TextView titleField;
    TextView startField;
    TextView endField;
    TextView mentorNameField;
    TextView mentorPhoneField;
    TextView mentorEmailField;
    final Calendar calendar = Calendar.getInstance();
    int field = 1;
    Spinner statusSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
        setTitle("Edit Course");

        titleField = findViewById(R.id.titleField);
        startField = findViewById(R.id.startField);
        endField = findViewById(R.id.endField);
        startField.setKeyListener(null);
        endField.setKeyListener(null);
        mentorNameField = findViewById(R.id.mentorNameField);
        mentorPhoneField = findViewById(R.id.mentorPhoneField);
        mentorEmailField = findViewById(R.id.mentorEmailField);

        titleField.setText(getIntent().getStringExtra("COURSE_TITLE"));
        startField.setText(getIntent().getStringExtra("COURSE_START"));
        endField.setText(getIntent().getStringExtra("COURSE_END"));
        mentorNameField.setText(getIntent().getStringExtra("MENTOR_NAME"));
        mentorPhoneField.setText(getIntent().getStringExtra("MENTOR_PHONE"));
        mentorEmailField.setText(getIntent().getStringExtra("MENTOR_EMAIL"));

        statusSpinner = findViewById(R.id.statusSpinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.statusArray, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(arrayAdapter);
        String courseStatus = getIntent().getStringExtra("COURSE_STATUS");
        switch(courseStatus){
            case "PLAN_TO_TAKE":
                statusSpinner.setSelection(0);
                break;
            case "IN_PROGESS":
                statusSpinner.setSelection(1);
                break;
            case "COMPLETED":
                statusSpinner.setSelection(2);
                break;
            case "DROPPED":
                statusSpinner.setSelection(3);
                break;

        }

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
                new DatePickerDialog(EditCourseActivity.this, dateSetListener, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        endField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                field = 2;
                new DatePickerDialog(EditCourseActivity.this, dateSetListener, calendar.get(Calendar.YEAR),
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
        Status courseStatus = Status.PLAN_TO_TAKE;
        String title = titleField.getText().toString();
        String start = startField.getText().toString();
        String end = endField.getText().toString();
        String mentorName = mentorNameField.getText().toString();
        String mentorPhone = mentorPhoneField.getText().toString();
        String mentorEmail = mentorEmailField.getText().toString();
        long id = getIntent().getLongExtra("COURSE_ID", 0);
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
        DBDriver.updateCourse(this, id, title, start, end, courseStatus, mentorName, mentorPhone,
                mentorEmail);
        finish();
    }
}
