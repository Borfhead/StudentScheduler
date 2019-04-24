package com.example.studentscheduler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

public class TermAddFragment extends Fragment{
    Context context;
    boolean isVisible = false;
    boolean isActive = false;
    public TermAddFragment(){
        super();
    }

    public TermFragment newInstance(int index){
        TermFragment fragment = new TermFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume(){
        super.onResume();
        isActive = true;
        if(isVisible && isActive){
            openTermAddScreen();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
        }
        else{
            isVisible = false;
        }
        if(isVisible && isActive){
            openTermAddScreen();
        }
    }

    private void openTermAddScreen(){
        if(getContext() != null){
            Intent intent = new Intent(getContext(), AddTermActivity.class);
            startActivity(intent);
            isVisible = false;
            isActive = false;
        }
    }
}
