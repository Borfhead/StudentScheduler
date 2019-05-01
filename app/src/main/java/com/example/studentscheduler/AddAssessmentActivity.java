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

public class AddAssessmentActivity extends AppCompatActivity {

    TextView titleField;
    TextView dueDateField;
    Spinner typeSpinner;
    Receiver receiver;
    static int requestCode;
    final Calendar calendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assessment);

        receiver = new Receiver();

        titleField = findViewById(R.id.titleField);
        dueDateField = findViewById(R.id.dueDateField);
        dueDateField.setKeyListener(null);
        typeSpinner = findViewById(R.id.typeSpinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.typeArray, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(arrayAdapter);

        final DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                populateField(dueDateField);
            }
        };

        dueDateField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddAssessmentActivity.this, dateSetListener, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    @Override
    public void onStop(){
        super.onStop();
        try{
            unregisterReceiver(receiver);
        }catch(IllegalArgumentException e){
            System.out.println("Couldn't unregister receiver");
        }

    }

    private void populateField(TextView toPopulate){
        String format = "MM/dd/yyyy";
        SimpleDateFormat simpleFormat = new SimpleDateFormat(format, Locale.getDefault());
        toPopulate.setText(simpleFormat.format(calendar.getTime()));
    }

    public void addBtnClicked(View view) {
        String title = titleField.getText().toString();
        String dueDate = dueDateField.getText().toString();
        Date date = null;
        AssessmentType type = AssessmentType.OBJECTIVE;
        long id = getIntent().getLongExtra("COURSE_ID", -1);
        switch(typeSpinner.getSelectedItemPosition()){
            case 0:
                type = AssessmentType.OBJECTIVE;
                break;
            case 1:
                type = AssessmentType.PERFORMANCE;
                break;
        }
        DBDriver.insertAssessment(this, title, dueDate, type, id);
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        try{
            date = format.parse(dueDate);
            setNotification(date.getTime(), "ASSESSMENT_DUE");
        } catch(Exception e){
            System.out.println("Couldn't parse date");
        }
        finish();
    }

    private void setNotification(long millis, String notificationType){
        IntentFilter filter = new IntentFilter("studentscheduler.action");
        registerReceiver(receiver, filter);
        AlarmManager alarms = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(AddAssessmentActivity.this, Receiver.class);
        intent.putExtra("NOTIFICATION_TYPE", notificationType);
        PendingIntent pendingStart = PendingIntent.getBroadcast(this, requestCode++, intent, 0);
        alarms.set(AlarmManager.RTC_WAKEUP, millis, pendingStart);
    }
}
