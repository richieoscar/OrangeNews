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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.adapter.LikesAdapter;
import com.richieoscar.orangenews.databinding.FragmentLikesBinding;
import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.viewmodel.LikesViewModel;

import java.util.ArrayList;
import java.util.Collections;

public class LikesFragment extends Fragment {

    private FragmentLikesBinding binding;
    private LikesViewModel viewModel;
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_likes, container, false);
        layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        decoration = new DividerItemDecoration(getContext(), layoutManager.getOrientation());
        // Inflate the layout for this fragment
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).getSupportActionBar().setTitle("Likes");
            ((MainActivity) getActivity()).hideBottomNavigation();
        }
        viewModel = new ViewModelProvider(getActivity()).get(LikesViewModel.class);
        viewModel.getLikes().observe(getActivity(), articles -> {
            if (articles.size() == 0) {
                binding.noLikes.setVisibility(View.VISIBLE);
                binding.imageBook.setVisibility(View.VISIBLE);
            }
            Collections.reverse(articles);
            setUpRecyclerView((ArrayList<Article>) articles);
        });

        return binding.getRoot();
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.search).setVisible(false);
        menu.findItem(R.id.clear_likes).setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.clear_likes:
                viewModel.clearLikes();
                Toast.makeText(getContext(), "Cleared Likes", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpRecyclerView(ArrayList<Article> articles) {
        LikesAdapter adapter = new LikesAdapter(articles);
        binding.likesRecyclerView.addItemDecoration(decoration);
        binding.likesRecyclerView.setAdapter(adapter);
        binding.likesRecyclerView.setLayoutManager(layoutManager);
    }
}