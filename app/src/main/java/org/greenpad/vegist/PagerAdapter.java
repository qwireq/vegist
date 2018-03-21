package org.greenpad.vegist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dias Issa on 21.03.2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    List <SemesterFragment> mFragmentsList;
    private String TAG = "PagerAdapter";
    public PagerAdapter(FragmentManager fm, int n) {
        super(fm);
        mNumOfTabs = n;
        mFragmentsList = new ArrayList<>();
        for(int i = 0; i < n; i++){
            SemesterFragment sf = new SemesterFragment();
            Bundle args = new Bundle();
            sf.setArguments(args);
            sf.setNumOfSemester(i);
            mFragmentsList.add(sf);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    public void setSemesters(Fragment f){
        ViewGroup viewGroup = (ViewGroup)  f.getView();

        Log.d(TAG, "Viewgroup " + f + " is " + viewGroup);
        //TextView childAt = (TextView) ((ViewGroup) viewGroup).getChildAt(0);
        //childAt.setText("Semester " + i);

    }
}
