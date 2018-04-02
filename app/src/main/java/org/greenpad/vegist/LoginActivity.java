package org.greenpad.vegist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText pword;
    Button sign_in;
    TextView err;
    RequestQueue requestQueue;
    SharedPreferences authPref;
    SharedPreferences.Editor authEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        authPref = PreferenceManager.getDefaultSharedPreferences(this);
        authEdit = authPref.edit();

        int isLogged = authPref.getInt("isLogged", 0);
        String strRes = authPref.getString("res", "");

        if (isLogged == 1) {
            //user is logged in
            Intent mainInt = new Intent(LoginActivity.this, MainActivity.class);
            mainInt.putExtra("data", strRes);
            startActivity(mainInt);
            finish();
        } else {

            email = findViewById(R.id.login_email);
            pword = findViewById(R.id.login_pword);
            sign_in = findViewById(R.id.login_button);
            err = findViewById(R.id.login_err);

            requestQueue = Volley.newRequestQueue(this);


            final AlphaAnimation anim = new AlphaAnimation(1F, 0F);
            anim.setDuration(200);

            this.sign_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sign_in.startAnimation(anim);
                    if ((email.getText().length() > 0) && (pword.getText().length() > 0)) {

                        sign_in.setEnabled(false);

                        StringRequest postRequest = new StringRequest(Request.Method.POST, "https://reg-fake-api.herokuapp.com/login",
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        // response
                                        Log.d("Response", response);
                                        try {
                                            JSONObject res = new JSONObject(response);
                                            if (res.getBoolean("ok")) {
                                                System.out.println("Sucess!");
                                                //Log.e("DATA", response);
                                                email.setText("");
                                                pword.setText("");
                                                err.setText("");
                                                //log_button.setEnabled(true);
                                                Intent mainInt = new Intent(LoginActivity.this, MainActivity.class);
                                                mainInt.putExtra("data", res.getString("data"));

                                                // sharedPref

                                                authEdit.putInt("isLogged", 1);
                                                authEdit.putString("res", res.getString("data"));
                                                authEdit.commit();
                                                // end of sharedPref

                                                startActivity(mainInt);

                                                finish();

                                            } else {
                                                email.setText("");
                                                pword.setText("");
                                                err.setText(res.getString("err"));
                                                sign_in.setEnabled(true);
                                            }
                                        } catch (Throwable e) {
                                            System.out.println();
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        // error
                                        Log.e("Error.Response", error.toString());
                                        err.setText("Can't connect to server. Please check internet connection and restart the app");
                                        sign_in.setEnabled(true);
                                    }
                                }
                        ) {
                            @Override
                            protected Map<String, String> getParams() {
                                Map<String, String> params = new HashMap<String, String>();
                                params.put("email", email.getText().toString());
                                params.put("pword", pword.getText().toString());

                                return params;
                            }
                        };
                        requestQueue.add(postRequest);
                        System.out.println("[SENT]: " + postRequest.toString());
                    }
                }
            });
        }
    }
}
