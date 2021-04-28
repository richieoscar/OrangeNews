package com.richieoscar.orangenews.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.repository.DataRepository;

import java.util.ArrayList;

public class EntertainmentViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Article>> article = new MutableLiveData<>();
    private LiveData<ArrayList<Article>> entertainmentArticles;
    private DataRepository repository = new DataRepository();

    public void fetch() {
        repository.fetchEntertainmentNews();
    }

    public LiveData<ArrayList<Article>> getEntertainmentArticles() {
        return entertainmentArticles = repository.entertainmentArticles();
    }
}
