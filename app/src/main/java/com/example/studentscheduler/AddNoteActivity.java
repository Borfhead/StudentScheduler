package com.example.studentscheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AddNoteActivity extends AppCompatActivity {
    TextView noteField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        noteField = findViewById(R.id.noteField);
    }

    public void addBtnClicked(View view) {
        String note = noteField.getText().toString();
        long id = getIntent().getLongExtra("COURSE_ID", -1);

        DBDriver.insertNote(this, note, id);
        finish();
    }
}
