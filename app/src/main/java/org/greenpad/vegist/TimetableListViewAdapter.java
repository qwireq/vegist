package org.greenpad.vegist;

import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

    public TimetableListViewAdapter(Context ctx, ArrayList<String> courseArrayList) {
        this.ctx = ctx;
        this.lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.courseArrayList = courseArrayList;
        completeCoursesList = new ArrayList<>();
        for(int i = 0; i < 6; i++)completeCoursesList.add("");
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
        }

        //Find TextView control
        ViewGroup vg = (ViewGroup) view;
        final AutoCompleteTextView acTextView = (AutoCompleteTextView) vg.getChildAt(0);
        acTextView.setThreshold(1);
        //Set the adapter
        acTextView.setAdapter(adapter);


        Log.d(TAG, "acTextView is "+acTextView);
        return view;
    }
}
