package com.richieoscar.orangenews.ui.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
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
import com.richieoscar.orangenews.adapter.SourcesAdapter;
import com.richieoscar.orangenews.databinding.FragmentSourcesBinding;
import com.richieoscar.orangenews.model.Source;
import com.richieoscar.orangenews.model.SourceJsonResult;
import com.richieoscar.orangenews.ui.activities.MainActivity;
import com.richieoscar.orangenews.viewmodel.SourcesViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SourcesFragment extends Fragment {
    private FragmentSourcesBinding binding;
    private SourcesAdapter adapter;
    private RecyclerView recyclerView;
    private SourcesViewModel viewModel;
    private AlertDialog alertDialog;

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
            fetchSources();
            hideNetworkAlert();
        } else {
            showNetworkAlert();
            hideProgressBar();
            tryAgain();
        }
        return binding.getRoot();
    }

    private void fetchSources() {
        showProgressbar();
        Call<SourceJsonResult> call = viewModel.fetch();
        call.enqueue(new Callback<SourceJsonResult>() {
            @Override
            public void onResponse(Call<SourceJsonResult> call, Response<SourceJsonResult> response) {
                if (response.isSuccessful()) {
                    viewModel.setAllSources(response.body().getSources());
                    hideNetworkAlert();
                }
            }

            @Override
            public void onFailure(Call<SourceJsonResult> call, Throwable throwable) {
                poorNetworkAlert();
                hideProgressBar();
            }
        });
    }

    private void setUpRecyclerView(ArrayList<Source> sources) {
        recyclerView = binding.sourceRecyclerView;
        adapter = new SourcesAdapter(sources);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void hideProgressBar() {
        binding.progressBar.setVisibility(View.GONE);
        binding.progressLoading.setVisibility(View.GONE);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.search).setVisible(false);
        menu.findItem(R.id.settings).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }


    private void hideNetworkAlert() {
        viewModel.getAllSources().observe(getActivity(), articles -> {
            hideProgressBar();
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

    private void tryAgain() {
        binding.tryAgain.setOnClickListener(v -> {
            if (isNetworkConnected()) {
                hide();
                showProgressbar();
                fetchSources();
                hideNetworkAlert();
            } else {
                Toast.makeText(getActivity(), R.string.unable, Toast.LENGTH_SHORT).show();
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