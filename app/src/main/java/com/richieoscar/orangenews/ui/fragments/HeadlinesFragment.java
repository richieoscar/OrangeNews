package com.richieoscar.orangenews.ui.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.adapter.ArticleAdapter;
import com.richieoscar.orangenews.databinding.FragmentHeadlinesBinding;
import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.ui.activities.MainActivity;
import com.richieoscar.orangenews.viewmodel.HeadlineViewModel;

import java.util.ArrayList;


public class HeadlinesFragment extends Fragment {
    FragmentHeadlinesBinding binding;
    private HeadlineViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_headlines, container, false);
        viewModel = new ViewModelProvider(getActivity()).get(HeadlineViewModel.class);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.feeds);
            ((MainActivity) getActivity()).showBottomNavigation();
        }
        if (isNetworkConnected()) {
            viewModel.fetch();
            hideNetworkAlert();
        } else {
            showNetworkAlert();
            hideProgressbar();
            tryAgain();
        }

        return binding.getRoot();
    }

    private void hideNetworkAlert() {
        viewModel.getHeadlines().observe(getActivity(), articles -> {
            hideProgressbar();
            setUpRecyclerView(articles);
        });
    }

    private void showNetworkAlert() {
        binding.imageNetwork.setVisibility(View.VISIBLE);
        binding.networkText.setVisibility(View.VISIBLE);
        binding.tryAgain.setVisibility(View.VISIBLE);
    }

    private void hideProgressbar() {
        binding.headlineProgressBar.setVisibility(View.INVISIBLE);
        binding.progressLoading.setVisibility(View.INVISIBLE);
    }

    private void showProgressbar() {
        binding.headlineProgressBar.setVisibility(View.VISIBLE);
    }

    private void setUpRecyclerView(ArrayList<Article> articles) {
        ArticleAdapter adapter = new ArticleAdapter(articles);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        binding.headlineRecyclerView.setAdapter(adapter);
        binding.headlineRecyclerView.setLayoutManager(layoutManager);
    }

    private void hide() {
        binding.imageNetwork.setVisibility(View.INVISIBLE);
        binding.networkText.setVisibility(View.INVISIBLE);
        binding.tryAgain.setVisibility(View.INVISIBLE);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private void tryAgain() {
        binding.tryAgain.setOnClickListener(v -> {
            if (isNetworkConnected()) {
                hide();
                showProgressbar();
                viewModel.fetch();
                hideNetworkAlert();
            } else {
                Toast.makeText(getContext(), "Unable to Connect", Toast.LENGTH_SHORT).show();
            }
        });
    }
}