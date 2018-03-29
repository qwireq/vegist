package org.greenpad.vegist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

                List<String> selectedCourses = new ArrayList<>();
                for (int i = 0; i < adapter.getNumOfLessons(); i++){
                    AutoCompleteTextView ac = (AutoCompleteTextView) getViewByPosition(i, listView);
                    selectedCourses.add(ac.getText().toString());
                }
                Set<String> coursesSet = new HashSet<>();
                coursesSet.addAll(selectedCourses);
                Log.d(TAG, "Saved courses:" + selectedCourses);
                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                editor.putStringSet("Semester"+numOfSemester, coursesSet);
                editor.commit();

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
    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

}
