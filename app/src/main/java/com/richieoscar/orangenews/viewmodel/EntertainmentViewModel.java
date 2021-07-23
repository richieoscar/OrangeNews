package com.richieoscar.orangenews.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.model.JsonResult;
import com.richieoscar.orangenews.repository.DataRepository;

import java.util.ArrayList;

import retrofit2.Call;

public class EntertainmentViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Article>> _entertainmentArticles = new MutableLiveData<>();
    private DataRepository repository = new DataRepository();

    public Call<JsonResult> fetch() {
        return  repository.fetchEntertainmentNews();
    }

    public void setEntertainmentArticles(ArrayList<Article> articles){
        _entertainmentArticles.setValue(articles);
    }
    public LiveData<ArrayList<Article>> getEntertainmentArticles() {
        return _entertainmentArticles;
    }
}
