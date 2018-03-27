package org.greenpad.vegist;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by root on 3/27/18.
 */

public class AnounceRetriever extends AsyncTask<String, Void, JSONArray> {

    private AnounceActivity calledActivity;

    public AnounceRetriever(){

    }

    public AnounceRetriever(Context context){
        if(context instanceof AnounceActivity){
            calledActivity = (AnounceActivity) context;
        }
    }

    @Override
    protected JSONArray doInBackground(String... strings) {

        JSONArray arr = new JSONArray();

        try {
            Document doc = Jsoup.connect("https://registrar.nu.edu.kz/announcements").get();
            Elements anounces = doc.select(".view-content > div");
            for (Element elem : anounces){

                JSONObject jo = new JSONObject();

                Elements titles = elem.select(".views-field-title a");
                Element a_title = titles.first();
                String url = "https://registrar.nu.edu.kz"+a_title.attr("href");
                url = url.replace("\\/", "/");
                jo.put("url", url);
                jo.put("title", a_title.text());

                Elements dates = elem.select(".views-field-created span");
                Element date = dates.first();
                jo.put("date", date.text());

                arr.put(jo);
            }

        }catch (Exception e){
            Log.e("JSOUP", e.toString());
        }


        return arr;
    }



    @Override
    protected void onPostExecute(JSONArray jsonArray) {
        super.onPostExecute(jsonArray);
        Log.e("JSON", jsonArray.toString());
        calledActivity.onAnounceLoaded(jsonArray);

    }
}
