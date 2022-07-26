package course.examples.Services.MovieClient;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class VideoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        Intent intent = getIntent();
        String url = intent.getStringExtra("URL");

        WebView videoWebView = (WebView) findViewById(R.id.movieVideoWebView);
        videoWebView.getSettings().setJavaScriptEnabled(true);
        videoWebView.loadUrl(url);
        videoWebView.setWebViewClient(new WebViewClient());
    }
}