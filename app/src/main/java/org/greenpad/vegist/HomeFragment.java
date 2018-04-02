package org.greenpad.vegist;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONObject;


public class HomeFragment extends Fragment {

    public TextView name;
    public TextView gpa;
    public TextView current;
    public TextView closed;
    public TextView major;
    public TextView s_id;
    public TextView year;
    public TextView admission;
    public JSONObject user;


    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        Log.e("RES", "asdasdasdasdasdasd");

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences((MainActivity) getActivity());
        String json = sharedPref.getString("res", "");

        name = view.findViewById(R.id.prof_name);
        gpa = view.findViewById(R.id.gpa);
        current = view.findViewById(R.id.courses);
        closed = view.findViewById(R.id.closed);
        major = view.findViewById(R.id.major);
        s_id = view.findViewById(R.id.stud_id);
        year = view.findViewById(R.id.year);
        admission = view.findViewById(R.id.admission);

        Log.e("RES2", json + " hasdasdsad");

        Log.e("activity", getActivity().toString());


        try {
            user = new JSONObject(json);

            name.setText(user.getString("name"));
            gpa.setText(user.get("gpa").toString());
            current.setText(user.get("current_credits").toString());
            closed.setText(user.get("all_credits").toString());
            major.setText(user.getString("p_major"));
            s_id.setText(user.getString("sid"));
            admission.setText(user.getString("adm_year"));
            year.setText(user.getString("year"));


        }catch (Exception x){
            x.printStackTrace();
        }



        // Inflate the layout for this fragment
        return view;
    }

}
