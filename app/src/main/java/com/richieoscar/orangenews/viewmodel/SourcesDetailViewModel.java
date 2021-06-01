package com.richieoscar.orangenews.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.model.Source;
import com.richieoscar.orangenews.repository.SourcesRepository;

import java.util.ArrayList;

public class SourcesViewModel extends ViewModel {

    private LiveData<ArrayList<Source>> allSources;
    private SourcesRepository repository = new SourcesRepository();

    public void fetch() {
        repository.fetchAllSources();
    }

    public LiveData<ArrayList<Source>> getAllSources() {
        return allSources = repository.getAllSources();
    }
}