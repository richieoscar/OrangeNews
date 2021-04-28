package com.richieoscar.orangenews.repository;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.richieoscar.orangenews.ApiClient;
import com.richieoscar.orangenews.NewsApi;
import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.model.JsonResult;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {
    private static final String API_KEY = "11aa189af1c04dc5a5c9ee37aa43ef9f";
    private static final String TAG = "DataRepository";
    public static final int PAGE_SIZE = 50;
    private MutableLiveData<ArrayList<Article>> headlineArticles = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Article>> latestNewsArticles = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Article>> sportNewsArticles = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Article>> entertainmentArticles = new MutableLiveData<>();

    public void fetchHeadlineNews() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            NewsApi connect = ApiClient.getApiInstance().create(NewsApi.class);
            Call<JsonResult> call = connect.getHeadlines("ng", "general", PAGE_SIZE, API_KEY);
            call.enqueue(new Callback<JsonResult>() {
                @Override
                public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                    if (response.isSuccessful()) {
                        headlineArticles.setValue(response.body().getArticles());
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

    public void fetchLatestNews() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            NewsApi connect = ApiClient.getApiInstance().create(NewsApi.class);
            Call<JsonResult> call = connect.getResults("general", PAGE_SIZE, API_KEY);
            call.enqueue(new Callback<JsonResult>() {
                @Override
                public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                    if (response.isSuccessful()) {
                        latestNewsArticles.setValue(response.body().getArticles());
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

    public void fetchSportsNews() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            NewsApi connect = ApiClient.getApiInstance().create(NewsApi.class);
            Call<JsonResult> call = connect.getSportsNews("us","sports",PAGE_SIZE, API_KEY);
            call.enqueue(new Callback<JsonResult>() {
                @Override
                public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                    if (response.isSuccessful()) {
                        sportNewsArticles.setValue(response.body().getArticles());
                        Log.d(TAG, "onResponse: running on " + Thread.currentThread().getName()+response.body().getArticles().toString());
                    } else {
                        Log.d(TAG, "onResponse: sports news" + response.code());
                    }
                }

                @Override
                public void onFailure(Call<JsonResult> call, Throwable t) {
                    Log.d(TAG, "onFailure:sports news failed");
                }
            });
        });
    }

    public void fetchEntertainmentNews() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            NewsApi connect = ApiClient.getApiInstance().create(NewsApi.class);
            Call<JsonResult> call = connect.getEntertainmentNews("us","entertainment",PAGE_SIZE, API_KEY);
            call.enqueue(new Callback<JsonResult>() {
                @Override
                public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                    if (response.isSuccessful()) {
                        entertainmentArticles.setValue(response.body().getArticles());
                        Log.d(TAG, "onResponse: running on " + Thread.currentThread().getName()+response.body().getArticles().toString());
                    } else {
                        Log.d(TAG, "onResponse: sports news" + response.code());
                    }
                }

                @Override
                public void onFailure(Call<JsonResult> call, Throwable t) {
                    Log.d(TAG, "onFailure:sports news failed");
                }
            });
        });
    }



    public MutableLiveData<ArrayList<Article>> headlineArticles() {
        return headlineArticles;
    }

    public MutableLiveData<ArrayList<Article>> latestNewsArticles() {
        return latestNewsArticles;
    }

    public MutableLiveData<ArrayList<Article>> sportNewsArticles() {
        return sportNewsArticles;
    }

    public MutableLiveData<ArrayList<Article>> entertainmentArticles() {
        return entertainmentArticles;
    }


}
