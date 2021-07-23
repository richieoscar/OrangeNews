package com.richieoscar.orangenews.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.model.JsonResult;
import com.richieoscar.orangenews.repository.DataRepository;

import java.util.ArrayList;

import retrofit2.Call;

public class LatestViewModel extends ViewModel {
    private LiveData<ArrayList<Article>> latestNewsArticles = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Article>> _latestNewsArticles = new MutableLiveData<>();
    private DataRepository repository = new DataRepository();

    public Call<JsonResult> fetch() {
       Call<JsonResult> call = repository.fetchLatestNews();
       return call;
    }

    public LiveData<ArrayList<Article>> getLatestNews() {
        return _latestNewsArticles;
    }
    public void setLatestNewsArticles(ArrayList<Article> articles){
        _latestNewsArticles.setValue(articles);
    }


}
