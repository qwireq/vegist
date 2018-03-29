package org.greenpad.vegist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.greenpad.vegist.anouncements.AnounceActivity;
import org.json.JSONArray;


public class DashboardFragment extends Fragment {
    private String data;

    public DashboardFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View z = inflater.inflate(R.layout.fragment_dashboard, container, false);

        Button b = z.findViewById(R.id.timetableButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToTimetable();
            }
        });
        //Button to launch anounce activity
        Button a_button = z.findViewById(R.id.a_button);
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

        //it.putExtra("data", data);
        startActivity(it);
    }
    public void goToAnounce(){
        Intent it = new Intent(getContext(), AnounceActivity.class);
        startActivity(it);
    }
}
