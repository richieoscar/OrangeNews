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
import com.richieoscar.orangenews.databinding.FragmentBusinessBinding;
import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.viewmodel.BusinessViewModel;

import java.util.ArrayList;

public class BusinessFragment extends Fragment {

    private FragmentBusinessBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_business, container, false);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).getSupportActionBar().setTitle("Business Feeds");
        }
        BusinessViewModel viewModel = new ViewModelProvider(getActivity()).get(BusinessViewModel.class);
        viewModel.fetch();
        viewModel.getBusinessNews().observe(getActivity(), articles -> {
            hideProgressbar();
            setUpRecyclerView(articles);
        });
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
}