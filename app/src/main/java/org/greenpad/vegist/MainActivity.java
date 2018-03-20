package org.greenpad.vegist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity{

    private TextView mTextMessage;
    public CourseDatabase cd;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    try {
                        if(cd.getData() != null){
                            mTextMessage.setText(cd.getData().get(0).toString());
                        }else {
                            mTextMessage.setText("Not ready!");
                        }
                    }catch (Exception e){
                        mTextMessage.setText(e.toString());
                    }


                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CourseRetriever cr = new CourseRetriever(this);
        cr.execute("foo");

        try {
            JSONObject userJsonContext = new JSONObject(getIntent().getStringExtra("data"));


            mTextMessage = (TextView) findViewById(R.id.message);
            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
            mTextMessage.setText("Hello, " + userJsonContext.getString("name"));
        } catch(JSONException r){
            System.out.print(r);
        }

    }


}
