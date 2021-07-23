package com.richieoscar.orangenews.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.model.JsonResult;
import com.richieoscar.orangenews.repository.DataRepository;

import java.util.ArrayList;

import retrofit2.Call;

public class SearchViewModel extends ViewModel {
    private String query;
    private String filter;

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    private LiveData<ArrayList<Article>> searchArticles;
    private MutableLiveData<ArrayList<Article>> _searchArticles = new MutableLiveData<>();
    private DataRepository repository = new DataRepository();

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setSearchArticles(ArrayList<Article> articles){
        _searchArticles.setValue(articles);
    }
    public Call<JsonResult> fetch() {
        repository.setQuery(getQuery());
        repository.setFilter(getFilter());
       return repository.searchNews();
    }

    public LiveData<ArrayList<Article>> getSearchResult() {
        return _searchArticles;
    }
}
