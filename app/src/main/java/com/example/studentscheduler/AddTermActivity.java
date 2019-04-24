package com.example.studentscheduler;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddTermActivity extends AppCompatActivity {

    TextView titleField;
    TextView startField;
    TextView endField;
    final Calendar calendar = Calendar.getInstance();
    int field = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);

        titleField = findViewById(R.id.titleField);
        startField = findViewById(R.id.startField);
        endField = findViewById(R.id.endField);

        startField.setKeyListener(null);
        endField.setKeyListener(null);

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
                new DatePickerDialog(AddTermActivity.this, dateSetListener, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        endField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                field = 2;
                new DatePickerDialog(AddTermActivity.this, dateSetListener, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        setTitle("Add Term");
    }

    private void populateField(TextView toPopulate){
        String format = "MM/dd/yyyy";
        SimpleDateFormat simpleFormat = new SimpleDateFormat(format, Locale.getDefault());
        toPopulate.setText(simpleFormat.format(calendar.getTime()));
    }


    public void addBtnClicked(View view) {
        String title = titleField.getText().toString();
        String start = startField.getText().toString();
        String end = endField.getText().toString();
        if(title.equals(null)){
            title = "No Title";
        }
        DBDriver.insertTerm(this, title, start, end);
        finish();
    }
}
