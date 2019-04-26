package com.example.studentscheduler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TermFragment extends Fragment {
    private Term associatedTerm;
    private ArrayList<Course> courses;
    private int index;
    public TermFragment(){
        super();
    }

    public TermFragment newInstance(int index, Term associatedTerm){
        TermFragment fragment = new TermFragment();
        Bundle args = new Bundle();
        this.associatedTerm = associatedTerm;
        args.putInt("index", index);
        this.index = index;
        args.putString("title", associatedTerm.getTitle());
        args.putString("start", associatedTerm.getStart());
        args.putString("end", associatedTerm.getEnd());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v;
        v = inflater.inflate(R.layout.term_layout, container, false);

        courses = DBDriver.getCoursesByTerm(v.getContext(), associatedTerm.getId());
        courses.add(new Course("Add Course"));

        TextView titleView = v.findViewById(R.id.titleField);
        TextView startView = v.findViewById(R.id.startField);
        TextView endView = v.findViewById(R.id.endField);
        titleView.setText(associatedTerm.getTitle());
        startView.setText(associatedTerm.getStart());
        endView.setText(associatedTerm.getEnd());

        final ListView listView = v.findViewById(R.id.courseListView);
        ArrayAdapter<Course> adapter = new ArrayAdapter<Course>(v.getContext(),
                android.R.layout.simple_list_item_1, courses);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(listView.getItemAtPosition(position).toString().equals("Add Course")){
                    Intent intent = new Intent(v.getContext(), AddCourseActivity.class);
                    intent.putExtra("TERM_ID", associatedTerm.getId());
                    startActivityForResult(intent, MainActivity.ADD_COURSE_CODE);
                }
                else{
                    Intent intent = new Intent(v.getContext(), ViewCourseActivity.class);
                    Course c = (Course)listView.getItemAtPosition(position);
                    intent.putExtra("COURSE_ID", c.getId());
                    startActivityForResult(intent, MainActivity.VIEW_COURSE_CODE);
                }
            }
        });
        return v;
    }
}

