package com.example.studentscheduler;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class TermCollectionPagerAdapter extends FragmentStatePagerAdapter {

    public static int num_items;
    private ArrayList<Term> allTerms;

    public TermCollectionPagerAdapter(Context context, FragmentManager fm){
        super(fm);
        DBDriver.populateAllTermsList(context);
        this.allTerms = DBDriver.allTerms;
        num_items = allTerms.size() + 1;
    }

    @Override
    public Fragment getItem(int i) {
        if(i == num_items - 1){
            TermAddFragment f = new TermAddFragment();
            f.newInstance(i);
            return f;
        }
        else{
            TermFragment f = new TermFragment();
            f.newInstance(i, allTerms.get(i));
            return f;
        }

    }

    @Override
    public int getCount() {
        return num_items;
    }

    @Override
    public CharSequence getPageTitle(int position){
        if(position == num_items - 1){
            return "+";
        }
        else{
            return allTerms.get(position).getTitle();
        }

    }
}
