package com.richieoscar.orangenews.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.repository.DataRepository;

import java.util.ArrayList;

public class HeadlineViewModel extends ViewModel {

    private LiveData<ArrayList<Article>> liveDataArticles;
    private DataRepository repository = new DataRepository();

    public void fetch() {
        repository.fetchHeadlineNews();
    }

    public LiveData<ArrayList<Article>> getHeadlines() {
        return liveDataArticles = repository.headlineArticles();
    }
}

