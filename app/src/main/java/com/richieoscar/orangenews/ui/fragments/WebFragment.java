package com.richieoscar.orangenews.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ShareCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.databinding.FragmentSourceDetailBinding;
import com.richieoscar.orangenews.databinding.FragmentWebBinding;
import com.richieoscar.orangenews.ui.activities.MainActivity;

public class WebFragment extends Fragment {
   private FragmentWebBinding binding;
   private WebView webView;
    private String url;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MainActivity activity = (MainActivity) getActivity();
        activity.getSupportActionBar().show();
        activity.hideBottomNavigation();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_web, container, false);
        webView = binding.webView;
        String name = getArguments().getString("NewsUrl");
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_INSIDE_OVERLAY);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        url = getArguments().getString("articleUrl");
        MainActivity activity = (MainActivity) getActivity();
        activity.getSupportActionBar().setTitle(url);
        if (url != null) {
            webView.loadUrl(url);
        }
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                webView.setVisibility(View.VISIBLE);
                binding.sourceProgressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.search).setVisible(false);
        menu.findItem(R.id.web_reload).setVisible(true);
        menu.findItem(R.id.web_share).setVisible(true);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                MainActivity activity = (MainActivity) getActivity();
                activity.onBackPressed();
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
        ShareCompat.IntentBuilder.from(getActivity())
                .setType("text/plain")
                .setText(url)
                .setChooserTitle(R.string.share_with)
                .startChooser();
    }
}