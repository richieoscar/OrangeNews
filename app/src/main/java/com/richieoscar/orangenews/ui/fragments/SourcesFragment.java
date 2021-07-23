package com.richieoscar.orangenews.ui.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.adapter.SourcesAdapter;
import com.richieoscar.orangenews.databinding.FragmentSourcesBinding;
import com.richieoscar.orangenews.model.Source;
import com.richieoscar.orangenews.ui.activities.MainActivity;
import com.richieoscar.orangenews.viewmodel.SourcesViewModel;

import java.util.ArrayList;

public class SourcesFragment extends Fragment {
    private FragmentSourcesBinding binding;
    private SourcesAdapter adapter;
    private RecyclerView recyclerView;
    private SourcesViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //call this method to enable the fragment have control in the menu bar
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MainActivity activity = (MainActivity) getActivity();
        activity.getSupportActionBar().show();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sources, container, false);
        // Inflate the layout for this fragment
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.sources);
            ((MainActivity) getActivity()).hideBottomNavigation();
        }
        viewModel = new ViewModelProvider(getActivity()).get(SourcesViewModel.class);
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

    private void setUpRecyclerView(ArrayList<Source> sources) {
        recyclerView = binding.sourceRecyclerView;
        adapter = new SourcesAdapter(sources);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    private void hideProgressBar(){
        binding.progressBar.setVisibility(View.GONE);
        binding.progressLoading.setVisibility(View.GONE);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.search).setVisible(false);
        menu.findItem(R.id.settings).setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Toast.makeText(getContext(), R.string.settings, Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void hideNetworkAlert() {
        viewModel.getAllSources().observe(getActivity(), articles -> {
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
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressbar() {
        binding.progressBar.setVisibility(View.INVISIBLE);
        binding.progressLoading.setVisibility(View.INVISIBLE);
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