package com.richieoscar.orangenews.ui.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.adapter.ArticleAdapter;
import com.richieoscar.orangenews.databinding.FragmentTechBinding;
import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.model.JsonResult;
import com.richieoscar.orangenews.ui.activities.MainActivity;
import com.richieoscar.orangenews.viewmodel.TechViewModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TechFragment extends Fragment {
    private FragmentTechBinding binding;
    private TechViewModel viewModel;
    private AlertDialog alertDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MainActivity activity = (MainActivity) getActivity();
        activity.getSupportActionBar().show();
        viewModel = new ViewModelProvider(getActivity()).get(TechViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tech, container, false);
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }


        if (isNetworkConnected()) {
            fetchTech();
        } else {
            showNetworkAlert();
            hideProgressbar();
            tryAgain();
        }
        return binding.getRoot();
    }
    @Override
    public void onResume() {
        MainActivity activity = (MainActivity) getActivity();
        activity.showBottomNavigation();
        super.onResume();
    }

    private void fetchTech() {
        showProgressbar();
        Call<JsonResult> call = viewModel.fetch();
        call.enqueue(new Callback<JsonResult>() {
            @Override
            public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                if (response.isSuccessful()) {
                    viewModel.setTechArticles(response.body().getArticles());
                    hideNetworkAlert();
                }
            }

            @Override
            public void onFailure(Call<JsonResult> call, Throwable throwable) {
                poorNetworkAlert();
                hideProgressbar();
            }
        });
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

    private void hideNetworkAlert() {
        viewModel.getTechNews().observe(getActivity(), articles -> {
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
        binding.techProgressBar.setVisibility(View.VISIBLE);
    }

    private void tryAgain() {
        binding.tryAgain.setOnClickListener(v -> {
            if (isNetworkConnected()) {
                hide();
                showProgressbar();
                fetchTech();
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