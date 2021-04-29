package com.richieoscar.orangenews.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.adapter.ArticleAdapter;
import com.richieoscar.orangenews.databinding.FragmentHeadlinesBinding;
import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.viewmodel.HeadlineViewModel;

import java.util.ArrayList;


public class HeadlinesFragment extends Fragment {
    FragmentHeadlinesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_headlines, container, false);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.feeds);
        }
        HeadlineViewModel viewModel = new ViewModelProvider(getActivity()).get(HeadlineViewModel.class);
        viewModel.fetch();
        viewModel.getHeadlines().observe(getActivity(), articles -> {
            hideProgressbar();
            setUpRecyclerView(articles);
        });
        return binding.getRoot();
    }

    private void hideProgressbar() {
        binding.headlineProgressBar.setVisibility(View.INVISIBLE);
        binding.progressLoading.setVisibility(View.INVISIBLE);
    }

    private void setUpRecyclerView(ArrayList<Article> articles) {
        ArticleAdapter adapter = new ArticleAdapter(articles);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        binding.headlineRecyclerView.setAdapter(adapter);
        binding.headlineRecyclerView.setLayoutManager(layoutManager);
    }
}