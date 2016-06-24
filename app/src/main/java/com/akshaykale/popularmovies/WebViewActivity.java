package com.akshaykale.popularmovies;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

/**
 * Created by Akshay on 6/24/2016.
 */
public class WebViewActivity extends AppCompatActivity {

    private static final String TAG = "WebViewActivity";
    WebView webview;

    Dialog progressDialog;

    String URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_webview);


        URL = getIntent().getStringExtra("webview_url");
        if (URL.equals("")) {
            Toast.makeText(getApplicationContext(),"Error.",Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Log.d(TAG,URL);
            webview = (WebView) findViewById(R.id.webview);

            progressDialog = new Dialog(WebViewActivity.this);
            progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            progressDialog.setContentView(R.layout.dialog_progress_bar);
            progressDialog.setCancelable(false);

            if (Utils.isOnline(getApplicationContext()))
                try {
                    loadURL();
                    Log.d("________", webview.getUrl() +"   "+ webview.getOriginalUrl());

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            else
                Toast.makeText(this, "No Internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadURL() throws UnsupportedEncodingException {

        webview.getSettings().setJavaScriptEnabled(true);

        final Activity activity = this;
        webview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                // Activities and WebViews measure progress with different scales.
                // The progress meter will automatically disappear when we reach 100%
            }
        });
        webview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressDialog.dismiss();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressDialog.show();
            }
        });

        webview.loadUrl(URL);

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            if (progressDialog.isShowing())
                progressDialog.dismiss();
            else
                webview.goBack();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
