package com.richieoscar.orangenews.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.repository.DataRepository;

import java.util.ArrayList;

public class SpainSportsViewModel extends ViewModel {
    private LiveData<ArrayList<Article>> sportNewsArticle;
    private DataRepository repository = new DataRepository();

    public void fetch() {
        repository.fetchSpainSportsNews();
    }

    public LiveData<ArrayList<Article>> getSportNews() {
        return sportNewsArticle = repository.getSpainSportNewsArticles();
    }
}
