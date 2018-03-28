package org.greenpad.vegist.courses;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by root on 3/20/18.
 */

public class CourseDatabase {

    private JSONArray data;

    public CourseDatabase(JSONArray d){
        data = d;
    }

    public JSONArray getData(){
        return data;
    }




}
