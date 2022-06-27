package com.richieoscar.orangenews.ui.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.richieoscar.orangenews.databinding.FragmentBusinessBinding;
import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.model.JsonResult;
import com.richieoscar.orangenews.ui.activities.MainActivity;
import com.richieoscar.orangenews.viewmodel.BusinessViewModel;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinessFragment extends Fragment {

    private FragmentBusinessBinding binding;
    private BusinessViewModel viewModel;
    private AlertDialog alertDialog;
    private static String TAG = "Business Fragment";
    Parcelable state;
    private LinearLayoutManager layoutManager;
    private ArrayList<Article> results;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = new ViewModelProvider(getActivity()).get(BusinessViewModel.class);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_business, container, false);

        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        if (getActivity() instanceof MainActivity) {
            Objects.requireNonNull(((MainActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
        }
//        if (isNetworkConnected()) {
//            fetchBusiness();
//            Log.i(TAG, "onCreateView: Makin network call");
//        } else {
//            showNetworkAlert();
//            hideProgressbar();
//            tryAgain();
//        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: onviewcreated called");
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        if (isNetworkConnected()) {
            fetchBusiness();
            //Log.i(TAG, "onCreateView: Makin network call");
        } else {
            showNetworkAlert();
            hideProgressbar();
            tryAgain();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: onResume called");
        MainActivity activity = (MainActivity) getActivity();
        activity.showBottomNavigation();
        if (state != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //displayAirticles();
                    binding.businessRecyclerView.getLayoutManager().onRestoreInstanceState(state);
                }
            }, 50);
            binding.businessRecyclerView.setLayoutManager(layoutManager);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: onPause called");
        state = Objects.requireNonNull(binding.businessRecyclerView.getLayoutManager()).onSaveInstanceState();
    }

    private void fetchBusiness() {
        showProgressbar();
        Call<JsonResult> call = viewModel.fetch();
        call.enqueue(new Callback<JsonResult>() {
            @Override
            public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                if (response.isSuccessful()) {
                    viewModel.setBusinessArticles(response.body().getArticles());
                    displayAirticles();
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
        binding.businessProgressBar.setVisibility(View.INVISIBLE);
        binding.businessLoading.setVisibility(View.INVISIBLE);
    }

    private void setUpRecyclerView(ArrayList<Article> businessNews) {
        ArticleAdapter adapter = new ArticleAdapter(businessNews);
        //layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        binding.businessRecyclerView.setAdapter(adapter);
        binding.businessRecyclerView.setLayoutManager(layoutManager);
    }

    private void displayAirticles() {
        viewModel.getBusinessNews().observe(getActivity(), articles -> {
            results = articles;
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
        binding.businessProgressBar.setVisibility(View.VISIBLE);
    }

    private void tryAgain() {
        binding.tryAgain.setOnClickListener(v -> {
            if (isNetworkConnected()) {
                hide();
                showProgressbar();
                fetchBusiness();
            } else {
                Toast.makeText(getContext(), "Unable to connect", Toast.LENGTH_SHORT).show();
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