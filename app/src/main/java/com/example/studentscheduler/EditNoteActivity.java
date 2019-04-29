package com.example.studentscheduler;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
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

    public void deleteBtnClicked(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == DialogInterface.BUTTON_POSITIVE){
                    DBDriver.deleteNote(EditNoteActivity.this, getIntent().getLongExtra("NOTE_ID", -1));
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
