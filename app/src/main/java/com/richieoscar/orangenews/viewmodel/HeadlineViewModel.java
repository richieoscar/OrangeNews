package com.richieoscar.orangenews.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.model.JsonResult;
import com.richieoscar.orangenews.repository.DataRepository;

import java.util.ArrayList;

import retrofit2.Call;

public class HeadlineViewModel extends ViewModel {


    private MutableLiveData<ArrayList<Article>>_headlineArticles = new MutableLiveData<>();
    private DataRepository repository = new DataRepository();

    public Call<JsonResult> fetch() {
        return repository.fetchHeadlineNews();
    }

public void setHeadlineArticles(ArrayList<Article> articles){
        _headlineArticles.setValue(articles);
}
    public LiveData<ArrayList<Article>> getHeadlines() {

        return _headlineArticles;
    }
}

