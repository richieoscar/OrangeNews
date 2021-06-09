package com.richieoscar.orangenews.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.repository.LikesRepository;

import java.util.List;

public class LikesViewModel extends AndroidViewModel {
    private LikesRepository repository;

    public LikesViewModel(@NonNull Application application) {
        super(application);
        repository = new LikesRepository(application);
    }

    public LiveData<List<Article>> getLikes() {
        return repository.getLikes();
    }

    public void clearLikes() {
        repository.clearLikes();
    }
}
