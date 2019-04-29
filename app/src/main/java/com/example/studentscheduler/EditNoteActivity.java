package com.example.studentscheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EditNoteActivity extends AppCompatActivity {
    TextView noteField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        noteField = findViewById(R.id.noteField);
        noteField.setText(getIntent().getStringExtra("NOTE_TEXT"));
    }

    public void addBtnClicked(View view) {
        String note = noteField.getText().toString();
        long id = getIntent().getLongExtra("NOTE_ID", -1);
        DBDriver.updateNote(this, note, id);
        finish();
    }
}
