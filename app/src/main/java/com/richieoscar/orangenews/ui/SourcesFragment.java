package com.richieoscar.orangenews.ui;

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
import com.richieoscar.orangenews.viewmodel.SourcesViewModel;

import java.util.ArrayList;

public class SourcesFragment extends Fragment {
    private FragmentSourcesBinding binding;
    private SourcesAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //call this method to enable the fragment have control in the menu bar
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sources, container, false);
        // Inflate the layout for this fragment
        if (getActivity() instanceof MainActivity) {
           ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.sources);
            ((MainActivity) getActivity()).hideBottomNavigation();
        }
        SourcesViewModel viewModel = new ViewModelProvider(getActivity()).get(SourcesViewModel.class);
        viewModel.fetch();
        viewModel.getAllSources().observe(getActivity(), sources -> {
            setUpRecyclerView(sources);
            hideProgressBar();
        });
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
        switch (item.getItemId()){
            case R.id.settings:
                Toast.makeText(getContext(), R.string.settings, Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}