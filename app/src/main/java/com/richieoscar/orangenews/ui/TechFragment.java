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
import com.richieoscar.orangenews.databinding.FragmentTechBinding;
import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.viewmodel.TechViewModel;

import java.util.ArrayList;


public class TechFragment extends Fragment {
    private FragmentTechBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tech, container, false);
        if(getActivity() instanceof MainActivity){
            ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.tech_feeds);
        }
        TechViewModel viewModel = new ViewModelProvider(getActivity()).get(TechViewModel.class);
        viewModel.fetch();
        viewModel.getTechNews().observe(getActivity(), articles -> {
            hideProgressbar();
            setUpRecyclerView(articles);
        });
        return binding.getRoot();
    }

    private void hideProgressbar() {
        binding.techProgressBar.setVisibility(View.INVISIBLE);
        binding.techLoading.setVisibility(View.INVISIBLE);
    }

    private void setUpRecyclerView(ArrayList<Article> techNews) {
        ArticleAdapter adapter = new ArticleAdapter(techNews);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        binding.techRecyclerView.setAdapter(adapter);
        binding.techRecyclerView.setLayoutManager(layoutManager);

    }
}