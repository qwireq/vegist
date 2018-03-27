package org.greenpad.vegist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class DashboardFragment extends Fragment {

    public DashboardFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View z = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ViewGroup vg = (ViewGroup) z;
        Button b = (Button) vg.getChildAt(0);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToTimetable();
            }
        });

        //Button to launch anounce activity
        Button a_button = (Button) vg.getChildAt(1);
        a_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                goToAnounce();
            }
        });

        return z;
    }
    public void goToTimetable(){
        Intent it = new Intent(getContext(), Timetable.class);
        startActivity(it);
    }

    public void goToAnounce(){
        Intent it = new Intent(getContext(), AnounceActivity.class);
        startActivity(it);
    }


}
