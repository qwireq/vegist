package org.greenpad.vegist;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by root on 3/20/18.
 */

public class CourseRetriever extends AsyncTask<String, String, JSONArray> {

    private MainActivity myContext;
    private JSONArray result;

    public CourseRetriever(MainActivity c){
        myContext = c;
    }


    @Override
    protected JSONArray doInBackground(String... strings) {


        RequestQueue requestQueue = Volley.newRequestQueue(myContext);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://reg-fake-api.herokuapp.com/courses",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        System.out.println(response);
                        try {
                            result = new JSONArray(response);
                        }catch (Exception e){
                            Log.e("JSON", e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("REQERROR", error.toString());
            }
        });

        requestQueue.add(stringRequest);

        while(result == null) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                Log.e("inter", e.toString());
            }
        }

        return result;
    }

    protected void onPostExecute(JSONArray data){
        myContext.cd = new CourseDatabase(data);
    }
}
