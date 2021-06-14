package com.richieoscar.orangenews.ui.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.databinding.DataBindingUtil;

import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.databinding.ActivityWebBinding;

public class WebActivity extends AppCompatActivity {

    private String url;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityWebBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_web);
        url = getIntent().getStringExtra("NewsUrl");
        getSupportActionBar().setTitle(url);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        webView = binding.webView;
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
        if (url != null) {
            webView.loadUrl(url);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.web_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.web_share:
                shareUrl();
                break;
            case R.id.web_reload:
                reload();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void reload() {
        webView.reload();
    }

    private void shareUrl() {
        ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText(url)
                .setChooserTitle(R.string.share_with)
                .startChooser();
    }
}