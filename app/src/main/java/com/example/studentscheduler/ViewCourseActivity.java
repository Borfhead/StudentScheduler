package com.example.studentscheduler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

//import com.facebook.stetho.common.ListUtil;

import java.util.ArrayList;

public class ViewCourseActivity extends AppCompatActivity {

    TextView titleField;
    TextView startField;
    TextView endField;
    TextView statusField;
    TextView nameField;
    TextView emailField;
    TextView phoneField;
    Button editBtn;
    ListView assessmentListView;
    ListView noteListView;
    ArrayList<Assessment> assessments;
    ArrayList<Note> notes;


    Course associatedCourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_course);

        setupActivity();
    }

    @Override
    public void onResume(){
        super.onResume();
        setupActivity();
    }

    private void setupActivity(){
        final long courseId = getIntent().getLongExtra("COURSE_ID", -1);
        associatedCourse = DBDriver.getCourse(this, courseId);

        assessments = DBDriver.getAssessmentByCourse(this, courseId);
        assessments.add(new Assessment("Add Assessment"));

        notes = DBDriver.getNotesByCourse(this, courseId);
        notes.add(new Note("Add Note"));

        titleField = findViewById(R.id.titleField);
        startField = findViewById(R.id.startField);
        endField = findViewById(R.id.endField);
        statusField = findViewById(R.id.statusField);
        nameField = findViewById(R.id.nameField);
        emailField = findViewById(R.id.emailField);
        phoneField = findViewById(R.id.phoneField);
        editBtn = findViewById(R.id.editBtn);
        assessmentListView = findViewById(R.id.assessmentListView);
        noteListView = findViewById(R.id.notesListView);

        titleField.setText(associatedCourse.getTitle());
        startField.setText(associatedCourse.getStart());
        endField.setText(associatedCourse.getEnd());
        statusField.setText(associatedCourse.getStatus().toString());
        nameField.setText(associatedCourse.getMentorName());
        emailField.setText(associatedCourse.getMentorEmail());
        phoneField.setText(associatedCourse.getMentorPhone());

        ArrayAdapter<Assessment> adapter = new ArrayAdapter<Assessment>(this,
                android.R.layout.simple_list_item_2, android.R.id.text1, assessments){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View v = super.getView(position, convertView, parent);
                TextView text1 = (TextView) v.findViewById(android.R.id.text1);
                TextView text2 = (TextView) v.findViewById(android.R.id.text2);

                text1.setText(assessments.get(position).toString());
                text2.setText(assessments.get(position).getDueDate());
                return v;
            }
        };
        assessmentListView.setAdapter(adapter);
        assessmentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(assessmentListView.getItemAtPosition(position).toString().equals("Add Assessment")){
                    Intent intent = new Intent(ViewCourseActivity.this, AddAssessmentActivity.class);
                    intent.putExtra("COURSE_ID", courseId);
                    startActivityForResult(intent, MainActivity.ADD_ASSESSMENT_CODE);
                }
                else{
//                    Open Assessment edit window
                    Intent intent = new Intent(ViewCourseActivity.this, EditAssessmentActivity.class);
                    Assessment a = (Assessment)assessmentListView.getItemAtPosition(position);
                    intent.putExtra("ASSESSMENT_ID", a.getId());
                    intent.putExtra("ASSESSMENT_TITLE", a.getTitle());
                    intent.putExtra("ASSESSMENT_DUE_DATE", a.getDueDate());
                    intent.putExtra("ASSESSMENT_TYPE", a.type.name());
                    startActivityForResult(intent, MainActivity.EDIT_ASSESSMENT_CODE);

                }
            }
        });

        ArrayAdapter<Note> noteAdapter = new ArrayAdapter<Note>(this,
                android.R.layout.simple_list_item_1, notes);
        noteListView.setAdapter(noteAdapter);
        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                if(noteListView.getItemAtPosition(position).toString().equals("Add Note")){
                    Intent intent = new Intent(ViewCourseActivity.this, AddNoteActivity.class);
                    intent.putExtra("COURSE_ID", courseId);
                    startActivityForResult(intent, MainActivity.ADD_NOTE_CODE);
                }
                else{
                    PopupMenu menu = new PopupMenu(ViewCourseActivity.this, view);
                    menu.getMenuInflater().inflate(R.menu.menu_note, menu.getMenu());
                    menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch(item.getTitle().toString()){
                                case "Edit":
//                                    Open note edit window
                                    Intent intent = new Intent(ViewCourseActivity.this, EditNoteActivity.class);
                                    Note n = (Note)noteListView.getItemAtPosition(position);
                                    intent.putExtra("NOTE_ID", n.getId());
                                    intent.putExtra("NOTE_TEXT", n.getNoteText());
                                    startActivityForResult(intent, MainActivity.EDIT_NOTE_CODE);
                                    break;
                                case "Share":
                                    //Share note
                                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                                    shareIntent.setType("text/plain");
                                    Note toSend = (Note)noteListView.getItemAtPosition(position);
                                    shareIntent.putExtra(Intent.EXTRA_TEXT, toSend.getNoteText());
                                    startActivity(Intent.createChooser(shareIntent, "Share Note"));
                                    break;
                            }
                            return true;
                        }
                    });

                    menu.show();
                }
            }
        });

        setListDynamicHeight(assessmentListView);
        setListDynamicHeight(noteListView);
    }

    private void setListDynamicHeight(ListView listView){
        ListAdapter adapter = listView.getAdapter();

        int height = 0;
        int width = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        for(int i = 0; i < adapter.getCount(); i++){
            View item = adapter.getView(i, null, listView);
            item.measure(width, View.MeasureSpec.UNSPECIFIED);
            height += item.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = height + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public void openEditWindow(View view) {
        Intent intent = new Intent(this, EditCourseActivity.class);
        intent.putExtra("COURSE_ID", associatedCourse.getId());
        intent.putExtra("COURSE_TITLE", associatedCourse.getTitle());
        intent.putExtra("COURSE_START", associatedCourse.getStart());
        intent.putExtra("COURSE_END", associatedCourse.getEnd());
        intent.putExtra("MENTOR_NAME", associatedCourse.getMentorName());
        intent.putExtra("MENTOR_EMAIL", associatedCourse.getMentorEmail());
        intent.putExtra("MENTOR_PHONE", associatedCourse.getMentorPhone());
        intent.putExtra("COURSE_STATUS", associatedCourse.getStatus().name());
        startActivityForResult(intent, MainActivity.EDIT_COURSE_CODE);
    }
}
