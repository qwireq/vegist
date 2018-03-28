package org.greenpad.vegist.anouncements;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.greenpad.vegist.R;
import org.json.JSONArray;
import org.json.JSONObject;

public class AnounceActivity extends AppCompatActivity implements AnounceFragment.OnListFragmentInteractionListener {

    private TextView txt;
    private AnounceRetriever ar;
    private FrameLayout fr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anounce);

        txt = findViewById(R.id.loading);
        fr = findViewById(R.id.anon_fragment);
        ar = new AnounceRetriever(this);
        ar.execute();

    }

    public void onAnounceLoaded(JSONArray arr){
        Log.e("ANON", "AnounceRetriever Finished");
        txt.setVisibility(View.GONE);
        Bundle bundle = new Bundle();
        bundle.putString("data", arr.toString());
        AnounceFragment af = new AnounceFragment();
        af.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.anon_fragment, af).commit();
    }

    @Override
    public void onListFragmentInteraction(JSONObject object) {
        Log.e("ANONCLICK", object.toString());
    }
}
