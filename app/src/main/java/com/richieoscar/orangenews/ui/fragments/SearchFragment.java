package com.richieoscar.orangenews.ui.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.adapter.ArticleAdapter;
import com.richieoscar.orangenews.databinding.FragmentSearchBinding;
import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.model.JsonResult;
import com.richieoscar.orangenews.ui.activities.MainActivity;
import com.richieoscar.orangenews.viewmodel.SearchViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {

    private SearchViewModel viewModel;
    private FragmentSearchBinding binding;
    private static final String TAG = "SearchFragment";
    private AlertDialog alertDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MainActivity activity = (MainActivity) getActivity();
        activity.getSupportActionBar().show();
        activity.hideBottomNavigation();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false);
        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        binding.searchResults.requestFocus();
        selectPopularity();
        selectRelevance();
        search();
        return binding.getRoot();
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.search).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }

    private void search() {
        binding.buttonSearch.setOnClickListener(v -> {
            if (isNetworkConnected()) {
                doSearch();
            } else {
                binding.searchResults.setText("Oops!, could not carry out search");
                binding.searchRecylerview.setVisibility(View.GONE);
                showNetworkAlert();
                tryAgain();
            }
        });
    }

    private void selectPopularity() {
        binding.popularity.setOnClickListener(v -> {
            binding.popularity.setBackgroundDrawable(getResources().getDrawable(R.drawable._selected));
            binding.relevancy.setBackgroundDrawable(getResources().getDrawable(R.drawable.text_shape));
            viewModel.setFilter(getString(R.string.popularity));
        });
    }

    private void selectRelevance() {
        binding.relevancy.setOnClickListener(v -> {
            binding.relevancy.setBackgroundDrawable(getResources().getDrawable(R.drawable._selected));
            binding.popularity.setBackgroundDrawable(getResources().getDrawable(R.drawable.text_shape));
            viewModel.setFilter(getString(R.string.relevancy));
        });
    }

    private void doSearch() {
        binding.progressBar2.setVisibility(View.VISIBLE);
        String query = binding.searchView.getText().toString();
        binding.searchResults.setText(R.string.searching);
        viewModel.setQuery(query);
        Call<JsonResult> call = viewModel.fetch();
        call.enqueue(new Callback<JsonResult>() {
            @Override
            public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                if (response.isSuccessful()) {
                    viewModel.setSearchArticles(response.body().getArticles());
                    hide();
                }
            }

            @Override
            public void onFailure(Call<JsonResult> call, Throwable throwable) {
                poorNetworkAlert();

            }
        });

        binding.searchView.setText("");
    }

    private void tryAgain() {
        binding.tryAgain.setOnClickListener(v -> {
            if (isNetworkConnected()) {
                hide();
                doSearch();
            } else {
                Toast.makeText(getContext(), R.string.unable, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpRecyclerView(ArrayList<Article> articles) {
        ArticleAdapter adapter = new ArticleAdapter(articles);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.searchRecylerview.setAdapter(adapter);
        binding.searchRecylerview.setLayoutManager(layoutManager);
        binding.searchRecylerview.setVisibility(View.VISIBLE);
    }

    private void poorNetworkAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.poor_network, null);
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
        Button ok = view.findViewById(R.id.button);
        ok.setOnClickListener((v) -> {
            alertDialog.dismiss();
            showNetworkAlert();
            tryAgain();
        });
    }

    private void showNetworkAlert() {
        binding.imageNetwork.setVisibility(View.VISIBLE);
        binding.networkText.setVisibility(View.VISIBLE);
        binding.tryAgain.setVisibility(View.VISIBLE);
    }

    private void hide() {
        viewModel.getSearchResult().observe(getActivity(), articles -> {
            setUpRecyclerView(articles);
            binding.searchResults.setText(getString(R.string.results) + " " + articles.size() + " " + getString(R.string.found) + viewModel.getQuery());
            binding.progressBar2.setVisibility(View.INVISIBLE);
            Log.d(TAG, "onCreate: results" + articles.size());
        });
        binding.imageNetwork.setVisibility(View.INVISIBLE);
        binding.networkText.setVisibility(View.INVISIBLE);
        binding.tryAgain.setVisibility(View.INVISIBLE);
    }


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


}