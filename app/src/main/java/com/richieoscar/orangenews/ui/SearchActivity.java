package com.richieoscar.orangenews.ui;

import android.os.Bundle;
import android.util.Log;

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
        search();
    }

    private void search() {
        binding.buttonSearch.setOnClickListener(v -> {
            query = binding.searchView.getText().toString();
            binding.searchResults.setText(R.string.searching);
            viewModel.setQuery(query);
            viewModel.fetch();
            viewModel.getSearchResult().observe(this, articles -> {
                setUpRecyclerView(articles);
                binding.searchResults.setText(getString(R.string.results) + articles.size() + getString(R.string.found) +viewModel.getQuery());
                Log.d(TAG, "onCreate: results" + articles.size());
            });
            binding.searchView.setText("");
        });
    }

    private void setUpRecyclerView(ArrayList<Article> articles) {
        adapter = new ArticleAdapter(articles);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.searchRecylerview.setAdapter(adapter);
        binding.searchRecylerview.setLayoutManager(layoutManager);
    }
}