package org.greenpad.vegist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;


import org.greenpad.vegist.courses.CourseActivity;
import org.greenpad.vegist.courses.CourseDatabase;
import org.greenpad.vegist.courses.CourseFragment;
import org.greenpad.vegist.courses.CourseRetriever;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements CourseFragment.OnListFragmentInteractionListener{

    public TextView mTextMessage;
    public CourseDatabase cd;
    private EditText searchText;
    private Button search;
    public FrameLayout fr;
    public BottomNavigationView bn;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    search.setVisibility(View.GONE);
                    searchText.setVisibility(View.GONE);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragplace, new HomeFragment()).commit();
                    fr.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_dashboard:
                    search.setVisibility(View.GONE);
                    searchText.setVisibility(View.GONE);
                    fr.setVisibility(View.VISIBLE);

                    JSONArray array = cd.getData();


                    Bundle bundle = new Bundle();

                    bundle.putString("data", array.toString());
                    // set Fragmentclass Arguments
                    DashboardFragment dashboardFragment = new DashboardFragment();
                    dashboardFragment.setArguments(bundle);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragplace, dashboardFragment).commit();

                    return true;
                //If Courses Clicked
                case R.id.navigation_notifications:
                    mTextMessage.setVisibility(View.GONE);
                    search.setVisibility(View.VISIBLE);
                    searchText.setVisibility(View.VISIBLE);

                    fr.setVisibility(View.GONE);

                    searchText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                        @Override
                        public void onFocusChange(View v, boolean hasFocus) {
                            searchText.setText("");
                        }
                    });

                    search.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(searchText.getText() != null){
                                try {
                                    if(cd.getData() != null){
                                        Bundle bundle = new Bundle();

                                        JSONArray filtered = new JSONArray();
                                        for(int i = 0; i<cd.getData().length(); i++){
                                            if(cd.getData().getJSONObject(i).getString("ABBR").toLowerCase().contains(searchText.getText().toString().toLowerCase()) ||
                                            cd.getData().getJSONObject(i).getString("TITLE").toLowerCase().contains(searchText.getText().toString().toLowerCase())){
                                                filtered.put(cd.getData().getJSONObject(i));
                                            }
                                            if(filtered.length() > 300)break;
                                        }

                                        bundle.putString("data", filtered.toString());
                                        bundle.putString("key", searchText.getText().toString());
                                        CourseFragment cf = new CourseFragment();
                                        cf.setArguments(bundle);
                                        getSupportFragmentManager().beginTransaction().replace(R.id.fragplace, cf).commit();
                                        fr.setVisibility(View.VISIBLE);
                                    }else {
                                        mTextMessage.setText("Not ready!");
                                    }
                                }catch (Exception e){
                                    mTextMessage.setText(e.toString());
                                }
                            }
                        }
                    });

                default:
                    return  true;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fr = findViewById(R.id.fragplace);
        bn = findViewById(R.id.navigation);
        mTextMessage = (TextView) findViewById(R.id.message);

        search = findViewById(R.id.filter);
        searchText = findViewById(R.id.filtertext);
        searchText.bringToFront();
        searchText.setVisibility(View.GONE);
        search.setVisibility(View.GONE);

        bn.setVisibility(View.GONE);
        fr.setVisibility(View.GONE);
        mTextMessage.setVisibility(View.VISIBLE);

        CourseRetriever cr = new CourseRetriever(this);
        cr.execute("foo");






        try {
            JSONObject userJsonContext = new JSONObject(getIntent().getStringExtra("data"));


            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
            //mTextMessage.setText("Hello, " + userJsonContext.getString("name"));
        } catch(JSONException r){
            System.out.print(r);
        }

    }


    @Override
    public void onListFragmentInteraction(JSONObject item) {
        try {
            Intent c_intent = new Intent(this, CourseActivity.class);
            c_intent.putExtra("data", item.toString());
            startActivity(c_intent);
        }catch (Exception c){
            Log.e("CLERR", c.toString());
        }

    }
}
