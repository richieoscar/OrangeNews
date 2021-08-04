package com.richieoscar.orangenews.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.richieoscar.orangenews.model.Source;
import com.richieoscar.orangenews.model.SourceJsonResult;
import com.richieoscar.orangenews.repository.SourcesRepository;

import java.util.ArrayList;

import retrofit2.Call;

public class SourcesViewModel extends ViewModel {


    private MutableLiveData<ArrayList<Source>> _allSources = new MutableLiveData<>();
    private SourcesRepository repository = new SourcesRepository();

    public Call<SourceJsonResult> fetch() {
        return  repository.fetchAllSources();
    }

    public LiveData<ArrayList<Source>> getAllSources() {
        return _allSources;
    }

    public void setAllSources(ArrayList<Source> sources){
        _allSources.setValue(sources);
    }
}
