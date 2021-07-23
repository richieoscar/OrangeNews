package com.richieoscar.orangenews.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.model.JsonResult;
import com.richieoscar.orangenews.repository.DataRepository;

import java.util.ArrayList;

import retrofit2.Call;

public class SportsUkViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Article>> _ukSportNewsArticles = new MutableLiveData<>();
    private DataRepository repository = new DataRepository();

    public Call<JsonResult> fetch() {
        return repository.fetchUkSportsNews();
    }

    public void setUkSportNewsArticles(ArrayList<Article> articles){
        _ukSportNewsArticles.setValue(articles);
    }
    public LiveData<ArrayList<Article>> getSportNews() {
        return _ukSportNewsArticles;
    }
}
