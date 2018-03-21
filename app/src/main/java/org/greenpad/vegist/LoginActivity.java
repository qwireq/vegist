package org.greenpad.vegist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.login_email);
        pword = findViewById(R.id.login_pword);
        sign_in = findViewById(R.id.login_button);
        err = findViewById(R.id.login_err);

        requestQueue = Volley.newRequestQueue(this);

        this.sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((email.getText().length() > 0) && (pword.getText().length() > 0)){

                    sign_in.setEnabled(false);

                    StringRequest postRequest = new StringRequest(Request.Method.POST, "https://reg-fake-api.herokuapp.com/login",
                            new Response.Listener<String>()
                            {
                                @Override
                                public void onResponse(String response) {

                                    // response
                                    Log.d("Response", response);
                                    try {
                                        JSONObject res = new JSONObject(response);
                                        if(res.getBoolean("ok")){
                                            System.out.println("Sucess!");
                                            email.setText("");
                                            pword.setText("");
                                            err.setText("");
                                            //log_button.setEnabled(true);
                                            Intent mainInt = new Intent(LoginActivity.this, MainActivity.class);
                                            mainInt.putExtra("data", res.getString("data"));
                                            startActivity(mainInt);

                                            finish();

                                        }else{
                                            email.setText("");
                                            pword.setText("");
                                            err.setText(res.getString("err"));
                                            sign_in.setEnabled(true);
                                        }
                                    }catch (Throwable e){
                                        System.out.println();
                                    }
                                }
                            },
                            new Response.ErrorListener()
                            {
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
                        protected Map<String, String> getParams()
                        {
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
