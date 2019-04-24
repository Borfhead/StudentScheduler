package com.example.studentscheduler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TermFragment extends Fragment {
    private Term associatedTerm;
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
        View v;
        v = inflater.inflate(R.layout.term_layout, container, false);
        TextView titleView = v.findViewById(R.id.titleField);
        TextView startView = v.findViewById(R.id.startField);
        TextView endView = v.findViewById(R.id.endField);
        titleView.setText(associatedTerm.getTitle());
        startView.setText(associatedTerm.getStart());
        endView.setText(associatedTerm.getEnd());
        return v;
    }
}

