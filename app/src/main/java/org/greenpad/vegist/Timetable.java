package org.greenpad.vegist;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.andexert.expandablelayout.library.ExpandableLayoutListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Timetable extends AppCompatActivity {
    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    private PagerAdapter mCollectionPagerAdapter;
    private ViewPager mViewPager;
    private android.support.v7.widget.Toolbar mToolbar;
    private ListView ListView;
    private TimetableListViewAdapter adapter;
    private ArrayList<String> coursesList;
    private JSONArray jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        String []js = getIntent().getStringExtra("data").split("#");
        coursesList = new ArrayList<>();
        for(int i = 0; i < js.length; i++)coursesList.add(js[i]);

        Log.d("Timetable", String.valueOf(coursesList.size()));
        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.
        //mCollectionPagerAdapter =
         //       new PagerAdapter( getSupportFragmentManager(), 8);
        //mViewPager = (ViewPager) findViewById(R.id.pager);
        //mViewPager.setAdapter(mCollectionPagerAdapter);
        mToolbar = findViewById(R.id.timetableToolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbar.setTitle("Timetable");
        }
        ListView = findViewById(R.id.listView);

        adapter = new TimetableListViewAdapter(this, coursesList);

        // создаем адаптер
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, coursesList);
        ListView.setAdapter(adapter);



    }
}

