package com.richieoscar.orangenews.repository;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import com.richieoscar.orangenews.api.ApiClient;
import com.richieoscar.orangenews.api.NewsApi;
import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.model.JsonResult;

import java.time.LocalDate;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DataRepository {
    private static final String API_KEY = "11aa189af1c04dc5a5c9ee37aa43ef9f";
    private static final String TAG = "DataRepository";
    private static final int PAGE_SIZE = 50;
    private LocalDate currentDate = LocalDate.now();
    private String from = currentDate.toString();
    private String to = currentDate.toString();
    private String query;
    private MutableLiveData<ArrayList<Article>> headlineArticles = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Article>> latestNewsArticles = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Article>> sportNewsArticles = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Article>> ukSportNewsArticles = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Article>> usSportNewsArticles = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Article>> germanySportNewsArticles = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Article>> spainSportNewsArticles = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Article>> entertainmentArticles = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Article>> techArticles = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Article>> businessArticles = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Article>> searchArticles = new MutableLiveData<>();

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

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
            Call<JsonResult> call = connect.getResults("general", "popularity", from, to, PAGE_SIZE, API_KEY);
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

    public void fetchLocalSportsNews() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            NewsApi connect = ApiClient.getApiInstance().create(NewsApi.class);
            Call<JsonResult> call = connect.getSportsNews("ng", "sports", null, PAGE_SIZE, API_KEY);
            call.enqueue(new Callback<JsonResult>() {
                @Override
                public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                    if (response.isSuccessful()) {
                        sportNewsArticles.setValue(response.body().getArticles());
                        Log.d(TAG, "onResponse: running on " + Thread.currentThread().getName() + response.body().getArticles().toString());
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

    public void fetchUkSportsNews() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            NewsApi connect = ApiClient.getApiInstance().create(NewsApi.class);
            Call<JsonResult> call = connect.getSportsNews("gb", "sports", null,  PAGE_SIZE, API_KEY);
            call.enqueue(new Callback<JsonResult>() {
                @Override
                public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                    if (response.isSuccessful()) {
                        ukSportNewsArticles.setValue(response.body().getArticles());
                        Log.d(TAG, "onResponse: running on " + Thread.currentThread().getName() + response.body().getArticles().toString());
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

    public void fetchUSSportsNews() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            NewsApi connect = ApiClient.getApiInstance().create(NewsApi.class);
            Call<JsonResult> call = connect.getSportsNews("us", "sports",null, PAGE_SIZE, API_KEY);
            call.enqueue(new Callback<JsonResult>() {
                @Override
                public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                    if (response.isSuccessful()) {
                        usSportNewsArticles.setValue(response.body().getArticles());
                        Log.d(TAG, "onResponse: running on " + Thread.currentThread().getName() + response.body().getArticles().toString());
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

    public void fetchGermanySportsNews() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            NewsApi connect = ApiClient.getApiInstance().create(NewsApi.class);
            Call<JsonResult> call = connect.getSportsNews("de", "sports", null, PAGE_SIZE, API_KEY);
            call.enqueue(new Callback<JsonResult>() {
                @Override
                public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                    if (response.isSuccessful()) {
                        germanySportNewsArticles.setValue(response.body().getArticles());
                        Log.d(TAG, "onResponse: running on " + Thread.currentThread().getName() + response.body().getArticles().toString());
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

    public void fetchSpainSportsNews() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            NewsApi connect = ApiClient.getApiInstance().create(NewsApi.class);
            Call<JsonResult> call = connect.getSportsNews("se", "sports", null, PAGE_SIZE, API_KEY);
            call.enqueue(new Callback<JsonResult>() {
                @Override
                public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                    if (response.isSuccessful()) {
                        spainSportNewsArticles.setValue(response.body().getArticles());
                        Log.d(TAG, "onResponse: running on " + Thread.currentThread().getName() + response.body().getArticles().toString());
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
            Call<JsonResult> call = connect.getEntertainmentNews("us", "entertainment", PAGE_SIZE, API_KEY);
            call.enqueue(new Callback<JsonResult>() {
                @Override
                public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                    if (response.isSuccessful()) {
                        entertainmentArticles.setValue(response.body().getArticles());
                        Log.d(TAG, "onResponse: running on " + Thread.currentThread().getName() + response.body().getArticles().toString());
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

    public void fetchTechNews() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            NewsApi connect = ApiClient.getApiInstance().create(NewsApi.class);
            Call<JsonResult> call = connect.getTechNews("us", "technology", PAGE_SIZE, API_KEY);
            call.enqueue(new Callback<JsonResult>() {
                @Override
                public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                    if (response.isSuccessful()) {
                        techArticles.setValue(response.body().getArticles());
                        Log.d(TAG, "onResponse: running on " + Thread.currentThread().getName() + response.body().getArticles().toString());
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

    public void fetchBusinessNews() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            NewsApi connect = ApiClient.getApiInstance().create(NewsApi.class);
            Call<JsonResult> call = connect.getBusinessNews("us", "business", PAGE_SIZE, API_KEY);
            call.enqueue(new Callback<JsonResult>() {
                @Override
                public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                    if (response.isSuccessful()) {
                        businessArticles.setValue(response.body().getArticles());
                        Log.d(TAG, "onResponse: running on " + Thread.currentThread().getName() + response.body().getArticles().toString());
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

    public void searchNews() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> {
            NewsApi connect = ApiClient.getApiInstance().create(NewsApi.class);
            Call<JsonResult> call = connect.getSearch(getQuery(), "popularity", PAGE_SIZE, API_KEY);
            call.enqueue(new Callback<JsonResult>() {
                @Override
                public void onResponse(Call<JsonResult> call, Response<JsonResult> response) {
                    if (response.isSuccessful()) {
                        searchArticles.setValue(response.body().getArticles());
                        Log.d(TAG, "onResponse: running on " + Thread.currentThread().getName() + response.body().getArticles().toString());
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

    public MutableLiveData<ArrayList<Article>> getHeadlineArticles() {
        return headlineArticles;
    }

    public MutableLiveData<ArrayList<Article>> getLatestNewsArticles() {
        return latestNewsArticles;
    }

    public MutableLiveData<ArrayList<Article>> getLocalSportNewsArticles() {
        return sportNewsArticles;
    }
    public MutableLiveData<ArrayList<Article>> getUsSportNewsArticles() {
        return usSportNewsArticles;
    }

    public MutableLiveData<ArrayList<Article>> getUkSportNewsArticles() {
        return ukSportNewsArticles;
    }

    public MutableLiveData<ArrayList<Article>> getSpainSportNewsArticles() {
        return spainSportNewsArticles;
    }

    public MutableLiveData<ArrayList<Article>> getGermanySportNewsArticles() {
        return germanySportNewsArticles;
    }

    public MutableLiveData<ArrayList<Article>> getEntertainmentArticles() {
        return entertainmentArticles;
    }

    public MutableLiveData<ArrayList<Article>> getTechArticles() {
        return techArticles;
    }

    public MutableLiveData<ArrayList<Article>> getBusinessArticles() {
        return businessArticles;
    }

    public MutableLiveData<ArrayList<Article>> getSearchArticles() {
        return searchArticles;
    }
}
