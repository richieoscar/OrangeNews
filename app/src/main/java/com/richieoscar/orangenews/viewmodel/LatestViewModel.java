package com.richieoscar.orangenews.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.repository.DataRepository;

import java.util.ArrayList;

public class LatestViewModel extends ViewModel {
    private LiveData<ArrayList<Article>> latestNewsArticles = new MutableLiveData<>();
    private static final String TAG = "LatestViewModel";
    private DataRepository repository = new DataRepository();

    public void fetch(){
        repository.fetchLatestNews();
    }

    public LiveData<ArrayList<Article>> getLatestNews() {
        return latestNewsArticles = repository.latestNewsArticles();
    }


}
