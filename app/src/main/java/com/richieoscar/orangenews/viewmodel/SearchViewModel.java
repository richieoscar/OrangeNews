package com.richieoscar.orangenews.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.repository.DataRepository;

import java.util.ArrayList;

public class SearchViewModel extends ViewModel {
    private String query;
    private LiveData<ArrayList<Article>> searchQuery;
    private DataRepository repository = new DataRepository();

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void fetch() {
        repository.setQuery(getQuery());
        repository.searchNews();
    }

    public LiveData<ArrayList<Article>> getSearchResult() {
        return searchQuery = repository.searchArticles();
    }

}
