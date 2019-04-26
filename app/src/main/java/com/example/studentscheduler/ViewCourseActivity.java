package com.example.studentscheduler;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.stetho.common.ListUtil;

import java.util.ArrayList;

public class ViewCourseActivity extends AppCompatActivity {

    TextView titleField;
    TextView startField;
    TextView endField;
    TextView statusField;
    TextView nameField;
    TextView emailField;
    TextView phoneField;
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
//                    Intent intent = new Intent(this, ViewCourseActivity.class);
//                    Course c = (Course)assessmentListView.getItemAtPosition(position);
//                    intent.putExtra("COURSE_ID", c.getId());
//                    startActivityForResult(intent, MainActivity.VIEW_COURSE_CODE);
                }
            }
        });



        ArrayAdapter<Note> noteAdapter = new ArrayAdapter<Note>(this,
                android.R.layout.simple_list_item_1, notes);
        noteListView.setAdapter(noteAdapter);
        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(noteListView.getItemAtPosition(position).toString().equals("Add Note")){
                    Intent intent = new Intent(ViewCourseActivity.this, AddNoteActivity.class);
                    intent.putExtra("COURSE_ID", courseId);
                    startActivityForResult(intent, MainActivity.ADD_NOTE_CODE);
                }
                else{
//                    Intent intent = new Intent(this, ViewCourseActivity.class);
//                    Course c = (Course)assessmentListView.getItemAtPosition(position);
//                    intent.putExtra("COURSE_ID", c.getId());
//                    startActivityForResult(intent, MainActivity.VIEW_COURSE_CODE);
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
}
