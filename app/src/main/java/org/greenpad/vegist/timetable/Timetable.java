package org.greenpad.vegist.timetable;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.greenpad.vegist.R;
import org.greenpad.vegist.courses.CourseDatabase;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import io.saeid.fabloading.LoadingView;

public class Timetable extends AppCompatActivity {
    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    private PagerAdapter mCollectionPagerAdapter;
    private ViewPager mViewPager;
    private Timetable this_activity;
    private android.support.v7.widget.Toolbar mToolbar;
    private ListView ListView;
    private TimetableListViewAdapter adapter;
    private ArrayList<String> coursesList;
    private CourseDatabase cd;
    private LoadingView loadingView;
    private String TAG = "Timetable";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        loadingView= findViewById(R.id.loading_view);
        loadingView.addAnimation(Color.CYAN,R.drawable.marvel_1,LoadingView.FROM_TOP);
        loadingView.addAnimation(Color.CYAN,R.drawable.marvel_2,LoadingView.FROM_LEFT);
        loadingView.addAnimation(Color.CYAN,R.drawable.marvel_3,LoadingView.FROM_RIGHT);
        loadingView.addAnimation(Color.CYAN,R.drawable.marvel_4,LoadingView.FROM_BOTTOM);
        loadingView.startAnimation();





        Log.d(TAG, "Loading finished");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String jsonArray = preferences.getString("data", "");
        JSONArray js = null;
        try {
            js = new JSONArray(jsonArray);
            coursesList = new ArrayList<>();
            String s="";
            for(int i = 0; i < js.length(); i++) {
                try {
                    s = js.getJSONObject(i).getString("TITLE");
                    coursesList.add(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            loadingView.setVisibility(View.INVISIBLE);
            mViewPager.setVisibility(View.VISIBLE);
            Log.d("Timetable", String.valueOf(coursesList.size()));
            // ViewPager and its adapters use support library
            // fragments, so use getSupportFragmentManager.
            mCollectionPagerAdapter =
                    new PagerAdapter( getSupportFragmentManager(), 12, coursesList);

            mViewPager.setAdapter(mCollectionPagerAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }



        mToolbar = findViewById(R.id.timetableToolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolbar.setTitle("Timetable");
        }
        /*
        ListView = findViewById(R.id.listView);

        adapter = new TimetableListViewAdapter(this, coursesList);

        // создаем адаптер
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.select_dialog_singlechoice, coursesList);
        ListView.setAdapter(adapter);
        */



    }
}

