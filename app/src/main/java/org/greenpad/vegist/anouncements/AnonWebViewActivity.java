package org.greenpad.vegist.anouncements;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.greenpad.vegist.R;

public class AnonWebViewActivity extends AppCompatActivity {

    public WebView myWW;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anon_web_view);

        url = this.getIntent().getStringExtra("url");

        myWW = findViewById(R.id.a_webview);
        myWW.getSettings().setJavaScriptEnabled(true);

        myWW.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });

        myWW.loadUrl(url);


    }
}
