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
import com.richieoscar.orangenews.databinding.FragmentEntertainmentBinding;
import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.viewmodel.EntertainmentViewModel;

import java.util.ArrayList;

public class EntertainmentFragment extends Fragment {
    FragmentEntertainmentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_entertainment, container, false);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.buzz_feed);
        }
        EntertainmentViewModel viewModel = new ViewModelProvider(getActivity()).get(EntertainmentViewModel.class);
        viewModel.fetch();
        viewModel.getEntertainmentArticles().observe(getActivity(), articles -> {
            hideProgressbar();
            setUpRecyclerView(articles);
        });
        return binding.getRoot();
    }

    private void hideProgressbar() {
        binding.entProgressBar.setVisibility(View.INVISIBLE);
        binding.progressLoadingEnt.setVisibility(View.INVISIBLE);
    }

    private void setUpRecyclerView(ArrayList<Article> entertainmentArticles) {
        ArticleAdapter adapter = new ArticleAdapter(entertainmentArticles);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        binding.entertainmentRv.setAdapter(adapter);
        binding.entertainmentRv.setLayoutManager(layoutManager);
    }
}