package org.greenpad.vegist.timetable;

import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.greenpad.vegist.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dias Issa on 23.03.2018.
 */

public class TimetableListViewAdapter extends BaseAdapter {
    private Context ctx;
    private LayoutInflater lInflater;
    private ArrayList<String> courseArrayList;
    private ArrayList<String> completeCoursesList;
    private ArrayAdapter<String> adapter;
    private List<AutoCompleteTextView> completeTextViewList;
    private String TAG = "TimetableAdapter";
    private int numOfLessons = 5;

    public TimetableListViewAdapter(Context ctx, ArrayList<String> courseArrayList) {
        this.ctx = ctx;
        this.lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.courseArrayList = courseArrayList;
        completeCoursesList = new ArrayList<>();
        for(int i = 0; i < numOfLessons; i++)completeCoursesList.add("");
        adapter = new ArrayAdapter<String>(ctx, android.R.layout.select_dialog_singlechoice, courseArrayList);
        completeTextViewList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return completeCoursesList.size();
    }

    @Override
    public Object getItem(int position) {


        return completeCoursesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые view
        View view = convertView;
        if (view == null) {
            Log.d(TAG, "View is null!!!!");
            view = lInflater.inflate(R.layout.timetable_item, parent, false);
            //Find TextView control
            final AutoCompleteTextView acTextView = (AutoCompleteTextView) view;
            acTextView.setThreshold(1);
            //Set the adapter
            acTextView.setAdapter(adapter);
            acTextView.setImeOptions(EditorInfo.IME_ACTION_DONE);
            completeTextViewList.add(acTextView);
        }


        return view;
    }

    public int getNumOfLessons() {
        return numOfLessons;
    }
}
