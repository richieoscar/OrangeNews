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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.adapter.SavedAdapter;
import com.richieoscar.orangenews.databinding.FragmentSavedBinding;
import com.richieoscar.orangenews.model.SavedArticle;
import com.richieoscar.orangenews.viewmodel.SavedArticleViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SavedFragment extends Fragment {
    private FragmentSavedBinding binding;
    private SavedArticleViewModel viewModel;
    private LinearLayoutManager layoutManager;
    private DividerItemDecoration decoration;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_saved, container, false);
        layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        decoration = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
        // Inflate the layout for this fragment
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).getSupportActionBar().setTitle("Saved Articles");
            ((MainActivity) getActivity()).hideBottomNavigation();
        }
        viewModel = new ViewModelProvider(getActivity()).get(SavedArticleViewModel.class);
        viewModel.getSavedArticles().observe(getActivity(), new Observer<List<SavedArticle>>() {
            @Override
            public void onChanged(List<SavedArticle> articles) {
                if (articles.size() == 0) {
                    binding.noSaved.setVisibility(View.VISIBLE);
                    binding.imageBook.setVisibility(View.VISIBLE);
                }
                Collections.reverse(articles);
                setUpRecyclerView((ArrayList<SavedArticle>) articles);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.search).setVisible(false);
        menu.findItem(R.id.clear_saved).setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear_saved:
                clearAll();
                Toast.makeText(getContext(), "Articles Deleted", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void clearAll() {
        viewModel.clearArticles();
    }

    private void setUpRecyclerView(ArrayList<SavedArticle> articles) {
        SavedAdapter adapter = new SavedAdapter(articles);
        binding.savedRecyclerView.addItemDecoration(decoration);
        binding.savedRecyclerView.setAdapter(adapter);
        binding.savedRecyclerView.setLayoutManager(layoutManager);
    }
}