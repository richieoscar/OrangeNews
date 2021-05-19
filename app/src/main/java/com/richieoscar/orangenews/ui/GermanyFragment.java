package com.richieoscar.orangenews.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.adapter.ArticleAdapter;
import com.richieoscar.orangenews.databinding.FragmentGermanyBinding;
import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.viewmodel.GermanySportsViewModel;
import com.richieoscar.orangenews.viewmodel.SportsUkViewModel;

import java.util.ArrayList;

public class GermanyFragment extends Fragment {
    private FragmentGermanyBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_germany, container, false);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.sports_feed);
        }
        GermanySportsViewModel viewModel = new ViewModelProvider(getActivity()).get(GermanySportsViewModel.class);
        viewModel.fetch();
        viewModel.getGermanySportNews().observe(getActivity(), articles -> {
            hideProgressbar();
            setUpRecyclerView(articles);
        });
        return  binding.getRoot();
    }

    private void hideProgressbar() {
        binding.sportsProgressBar.setVisibility(View.INVISIBLE);
        binding.sportsLoading.setVisibility(View.INVISIBLE);
    }

    private void setUpRecyclerView(ArrayList<Article> sportNews){
        ArticleAdapter adapter = new ArticleAdapter(sportNews);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        binding.sportsRecyclerView.setAdapter(adapter);
        binding.sportsRecyclerView.setLayoutManager(layoutManager);
    }
}