package com.example.studentscheduler;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
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

public class EditAssessmentActivity extends AppCompatActivity {

    TextView titleField;
    TextView dueDateField;
    Spinner typeSpinner;
    long assessmentId;
    final Calendar calendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assessment);
        assessmentId = getIntent().getLongExtra("ASSESSMENT_ID", -1);

        titleField = findViewById(R.id.titleField);
        dueDateField = findViewById(R.id.dueDateField);
        dueDateField.setKeyListener(null);
        typeSpinner = findViewById(R.id.typeSpinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.typeArray, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(arrayAdapter);

        titleField.setText(getIntent().getStringExtra("ASSESSMENT_TITLE"));
        dueDateField.setText(getIntent().getStringExtra("ASSESSMENT_DUE_DATE"));

        switch(getIntent().getStringExtra("ASSESSMENT_TYPE")){
            case "OBJECTIVE":
                typeSpinner.setSelection(0);
                break;
            case "PERFORMANCE":
                typeSpinner.setSelection(1);
                break;
        }

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
                new DatePickerDialog(EditAssessmentActivity.this, dateSetListener, calendar.get(Calendar.YEAR),
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
        String title = titleField.getText().toString();
        String dueDate = dueDateField.getText().toString();
        AssessmentType type = AssessmentType.OBJECTIVE;
        switch(typeSpinner.getSelectedItemPosition()){
            case 0:
                type = AssessmentType.OBJECTIVE;
                break;
            case 1:
                type = AssessmentType.PERFORMANCE;
                break;
        }
        DBDriver.updateAssessment(this, title, dueDate, type, assessmentId);
        finish();
    }

    public void deleteBtnClicked(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == DialogInterface.BUTTON_POSITIVE){
                    DBDriver.deleteAssessment(EditAssessmentActivity.this, assessmentId);
                    finish();
                }
            }
        };
        alert.setMessage("Are you sure you want to delete?");
        alert.setPositiveButton("Yes", listener);
        alert.setNegativeButton("No", listener);
        alert.show();
    }
}
