package com.richieoscar.orangenews.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.repository.DataRepository;

import java.util.ArrayList;

public class GermanySportsViewModel extends ViewModel {
    private LiveData<ArrayList<Article>> sportNewsArticle;
    private DataRepository repository = new DataRepository();

    public void fetch() {
        repository.fetchGermanySportsNews();
    }

    public LiveData<ArrayList<Article>> getGermanySportNews() {
        return sportNewsArticle = repository.getGermanySportNewsArticles();

    }
}
