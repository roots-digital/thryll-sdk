package tech.rootsdigital.thryll_integration_sdk.ui.webview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import tech.rootsdigital.thryll_integration_sdk.R;


public class WebviewActivity extends AppCompatActivity {

    WebView webview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));

        ProgressBar progressBar = findViewById(R.id.progress_bar);
        webview = findViewById(R.id.webview);

        webview.getSettings().setJavaScriptEnabled(true);

        final Activity activity = this;
        webview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                // Activities and WebViews measure progress with different scales.
                // The progress meter will automatically disappear when we reach 100%
                activity.setProgress(progress * 1000);

            }
        });

        webview.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                view.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                Toast.makeText(activity, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                view.setVisibility(View.VISIBLE);

                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                view.setVisibility(View.INVISIBLE);

                super.onPageStarted(view, url, favicon);
            }
        });

        webview.loadUrl(getIntent().getStringExtra("url"));
        Log.d("Thryll Webview", "Load Url: " + getIntent().getStringExtra("url"));
    }

    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if(webview.canGoBack())
            webview.goBack();
        else
            super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        webview.loadUrl("about:blank");
//        webview.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        webview.onResume();
    }
}
