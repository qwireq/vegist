package org.greenpad.vegist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dias Issa on 21.03.2018.
 */

public class SemesterFragment extends Fragment{

    private int numOfSemester;
    private String major;
    private Context mContext;
    private ViewGroup mRootView;
    private String TAG = "SemesterFragment";
    private ArrayList<String> coursesList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_collection_object, container, false);
        Log.d(TAG, " RootView is "+ rootView);

        mContext = inflater.getContext();
        mRootView = rootView;

        final ListView listView = (ListView) rootView.getChildAt(0);
        final TimetableListViewAdapter adapter = new TimetableListViewAdapter(mContext, coursesList);

        listView.setAdapter(adapter);
        Button b = (Button) rootView.getChildAt(1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimetableListViewAdapter listViewAdapter = (TimetableListViewAdapter) listView.getAdapter();

            }
        });
        /*
        ViewGroup viewGroup = (ViewGroup)  rootView;
        Log.d(TAG, "Viewgroup " + numOfSemester + " is " + viewGroup);
        TextView childAt = (TextView) ((ViewGroup) viewGroup).getChildAt(0);
        childAt.setText("Semester " + (numOfSemester+1));

        for(int j = 0; j < 5; j++){
            addCourse("semester " + (numOfSemester+1) + " , course " + (j+1));
        }
        */


        return rootView;
    }
    public void setNumOfSemester(int numOfSemester){
        this.numOfSemester = numOfSemester;
    }
    public int getNumOfSemester() {
        return numOfSemester;
    }
    public void setCoursesList(ArrayList<String> coursesList){
        this.coursesList = coursesList;
    }
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void addCourse(String course){
        if(mContext == null && mRootView == null) Log.d("SemesterFragment", "mContext & mRootView are NULL!!!");
        else if(mContext == null)Log.d("SemesterFragment", "mContext is NULL!!!");
        else if(mRootView == null)Log.d("SemesterFragment", "mRootView is NULL!!!");
        else {
            View t = LayoutInflater.from(mContext).inflate(R.layout.course_layout, null);
            ViewGroup viewGroup = (ViewGroup) t;
            for (int index = 0; index < ((ViewGroup) viewGroup).getChildCount(); ++index) {
                if(index == 0) {
                    TextView nextChild = (TextView) ((ViewGroup) viewGroup).getChildAt(index);
                    nextChild.setText("ABBR of " + course);
                }
                else{
                    TextView nextChild = (TextView) ((ViewGroup) viewGroup).getChildAt(index);
                    nextChild.setText(course);
                }
            }
            mRootView.addView(t);
        }
    }

}
