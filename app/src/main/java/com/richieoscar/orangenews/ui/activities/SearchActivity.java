package com.richieoscar.orangenews.ui.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.adapter.ArticleAdapter;
import com.richieoscar.orangenews.databinding.ActivitySearchBinding;
import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.viewmodel.SearchViewModel;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private SearchViewModel viewModel;
    private String query;
    private ActivitySearchBinding binding;
    private static final String TAG = "SearchActivity";
    private ArticleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        binding.searchResults.requestFocus();
        search();
    }

    private void search() {
        binding.buttonSearch.setOnClickListener(v -> {
            if (isNetworkConnected()) {
                hide();
                doSearch();
            } else {
                binding.searchResults.setText("Oops!, could not carry out search");
                binding.searchRecylerview.setVisibility(View.GONE);
                showNetworkAlert();
                tryAgain();
            }
        });
    }

    private void doSearch() {
        binding.progressBar2.setVisibility(View.VISIBLE);
        query = binding.searchView.getText().toString();
        binding.searchResults.setText(R.string.searching);
        viewModel.setQuery(query);
        viewModel.fetch();
        viewModel.getSearchResult().observe(this, articles -> {
            setUpRecyclerView(articles);
            binding.searchResults.setText(getString(R.string.results) + " " + articles.size() + " " + getString(R.string.found) + viewModel.getQuery());
            binding.progressBar2.setVisibility(View.INVISIBLE);
            Log.d(TAG, "onCreate: results" + articles.size());
        });
        binding.searchView.setText("");
    }

    private void tryAgain() {
        binding.tryAgain.setOnClickListener(v -> {
            if (isNetworkConnected()) {
                hide();
                doSearch();
            } else {
                Toast.makeText(this, "Unable to Connect", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpRecyclerView(ArrayList<Article> articles) {
        adapter = new ArticleAdapter(articles);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.searchRecylerview.setAdapter(adapter);
        binding.searchRecylerview.setLayoutManager(layoutManager);
        binding.searchRecylerview.setVisibility(View.VISIBLE);
    }

    private void showNetworkAlert() {
        binding.imageNetwork.setVisibility(View.VISIBLE);
        binding.networkText.setVisibility(View.VISIBLE);
        binding.tryAgain.setVisibility(View.VISIBLE);
    }

    private void hide() {
        binding.imageNetwork.setVisibility(View.INVISIBLE);
        binding.networkText.setVisibility(View.INVISIBLE);
        binding.tryAgain.setVisibility(View.INVISIBLE);
    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}