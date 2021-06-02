package com.richieoscar.orangenews.repository;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.richieoscar.orangenews.api.ApiClient;
import com.richieoscar.orangenews.api.NewsApi;
import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.model.JsonResult;
import com.richieoscar.orangenews.model.Source;
import com.richieoscar.orangenews.model.SourceJsonResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SourcesRepository {
    private static final String API_KEY = "11aa189af1c04dc5a5c9ee37aa43ef9f";
    private static final String TAG = "SourcesRepository";
    private static final int PAGE_SIZE = 50;
    private  String domain;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    private MutableLiveData<ArrayList<Source>> allSources = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Article>> fromSource = new MutableLiveData<>();


    public void fetchAllSources() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            NewsApi connect = ApiClient.getApiInstance().create(NewsApi.class);
            Call<SourceJsonResult> call = connect.getAllSources( API_KEY);
            call.enqueue(new Callback<SourceJsonResult>() {
                @Override
                public void onResponse(Call<SourceJsonResult> call, Response<SourceJsonResult> response) {
                    if (response.isSuccessful()) {
                        allSources.setValue(response.body().getSources());
                        Log.d(TAG, "onResponse: running on " + Thread.currentThread().getName());
                    } else {
                        Log.d(TAG, "onResponse: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<SourceJsonResult> call, Throwable t) {
                    Log.d(TAG, "onFailure: failed");
                }
            });
        });
    }

    public void fetchFromSources() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            NewsApi connect = ApiClient.getApiInstance().create(NewsApi.class);
            Call<JsonResult> call = connect.getFromSource(getDomain(), API_KEY);

            call.enqueue(new Callback<JsonResult>() {
                @Override
                public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                    Log.d(TAG, "onResponse: call" +response.errorBody());
                    if (response.isSuccessful()) {
                        fromSource.setValue(response.body().getArticles());
                        Log.d(TAG, "onResponse: running on " + Thread.currentThread().getName());
                    } else {
                        Log.d(TAG, "onResponse: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<JsonResult> call, Throwable t) {
                    Log.d(TAG, "onFailure: failed");
                }
            });
        });
    }

    public MutableLiveData<ArrayList<Source>> getAllSources() {
        return allSources;
    }
    public MutableLiveData<ArrayList<Article>> getFromSources() {
        return fromSource;
    }
}
