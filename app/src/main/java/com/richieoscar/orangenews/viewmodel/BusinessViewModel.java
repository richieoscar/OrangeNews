package com.richieoscar.orangenews.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.repository.DataRepository;

import java.util.ArrayList;

public class BusinessViewModel extends ViewModel {
    private LiveData<ArrayList<Article>> techArticles;
    private DataRepository repository = new DataRepository();

    public void fetch() {
        repository.fetchBusinessNews();
    }

    public LiveData<ArrayList<Article>> getBusinessNews() {
        return techArticles = repository.businessArticles();
    }
}
