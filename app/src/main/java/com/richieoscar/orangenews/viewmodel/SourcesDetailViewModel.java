package com.richieoscar.orangenews.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.model.Source;
import com.richieoscar.orangenews.repository.SourcesRepository;

import java.util.ArrayList;

public class SourcesDetailViewModel extends ViewModel {

    private LiveData<ArrayList<Article>> fromSources;
    private SourcesRepository repository = new SourcesRepository();

    public void fetch() {
        repository.fetchFromSources();
    }

    public LiveData<ArrayList<Article>> getFromSource() {
        return fromSources = repository.getFromSources();
    }
}
