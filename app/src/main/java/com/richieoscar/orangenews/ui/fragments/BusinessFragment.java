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
import com.richieoscar.orangenews.databinding.FragmentBusinessBinding;
import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.ui.activities.MainActivity;
import com.richieoscar.orangenews.viewmodel.BusinessViewModel;

import java.util.ArrayList;

public class BusinessFragment extends Fragment {

    private FragmentBusinessBinding binding;
    private BusinessViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_business, container, false);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.business_feeds);

        }
        viewModel = new ViewModelProvider(getActivity()).get(BusinessViewModel.class);
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

    private void hideProgressbar() {
        binding.businessProgressBar.setVisibility(View.INVISIBLE);
        binding.businessLoading.setVisibility(View.INVISIBLE);
    }

    private void setUpRecyclerView(ArrayList<Article> businessNews) {
        ArticleAdapter adapter = new ArticleAdapter(businessNews);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        binding.businessRecyclerView.setAdapter(adapter);
        binding.businessRecyclerView.setLayoutManager(layoutManager);
    }

    private void hideNetworkAlert() {
        viewModel.getBusinessNews().observe(getActivity(), articles -> {
            hideProgressbar();
            setUpRecyclerView(articles);
            hide();
        });
    }

    private void showNetworkAlert() {
        binding.imageNetwork.setVisibility(View.VISIBLE);
        binding.networkText.setVisibility(View.VISIBLE);
        binding.tryAgain.setVisibility(View.VISIBLE);
    }

    private void showProgressbar() {
        binding.businessProgressBar.setVisibility(View.VISIBLE);
    }

    private void tryAgain() {
        binding.tryAgain.setOnClickListener(v -> {
            if (isNetworkConnected()) {
                hide();
                showProgressbar();
                viewModel.fetch();
                hideNetworkAlert();
            } else {
                Toast.makeText(getActivity(), "Unable to connect", Toast.LENGTH_SHORT).show();
            }
        });
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
}