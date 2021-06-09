package com.richieoscar.orangenews.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.richieoscar.orangenews.model.SavedArticle;
import com.richieoscar.orangenews.repository.SavedArticleRepository;

import java.util.List;

public class SavedArticleViewModel extends AndroidViewModel {
    private SavedArticleRepository repository;

    public SavedArticleViewModel(@NonNull Application application) {
        super(application);
        repository = new SavedArticleRepository(application);
    }

    public LiveData<List<SavedArticle>> getSavedArticles() {
        return repository.getSavedArticles();
    }

    public void clearArticles() {
        repository.deleteAll();
    }
}
