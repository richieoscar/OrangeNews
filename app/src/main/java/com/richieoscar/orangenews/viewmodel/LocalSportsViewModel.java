package com.richieoscar.orangenews.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.model.JsonResult;
import com.richieoscar.orangenews.repository.DataRepository;

import java.util.ArrayList;

import retrofit2.Call;

public class LocalSportsViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Article>> _localSportNewsArticles = new MutableLiveData<>();
    private DataRepository repository = new DataRepository();

    public Call<JsonResult> fetch() {
        return repository.fetchLocalSportsNews();
    }
    public void setLocalSportNewsArticles(ArrayList<Article> articles){
        _localSportNewsArticles.setValue(articles);
    }
    public LiveData<ArrayList<Article>> getLocalSportNews() {
        return _localSportNewsArticles;
    }
}
