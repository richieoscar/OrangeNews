package com.richieoscar.orangenews.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.richieoscar.orangenews.db.OrangeDao;
import com.richieoscar.orangenews.db.OrangeNewsDatabase;
import com.richieoscar.orangenews.model.SavedArticle;

import java.util.List;

public class SavedArticleRepository {
    Context context;
    OrangeDao dao;
    OrangeNewsDatabase database;

    public SavedArticleRepository(Context context) {
        database = OrangeNewsDatabase.getOrangeNewsDatabaseInstance(context.getApplicationContext());
        dao = database.orangeDao();
    }

    public SavedArticleRepository() {
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
        database = OrangeNewsDatabase.getOrangeNewsDatabaseInstance(context);
        dao = database.orangeDao();
    }

    public void saveArticle(SavedArticle article) {
        OrangeNewsDatabase.doInBackground.execute(() -> dao.saveArticle(article));
    }

    public LiveData<List<SavedArticle>> getSavedArticles() {
        return dao.getSavedArticles();
    }

    public void deleteSavedArticle(SavedArticle article) {
        OrangeNewsDatabase.doInBackground.execute(() -> dao.deleteSavedArticle(article));
    }

    public void deleteAll() {
        OrangeNewsDatabase.doInBackground.execute(() -> dao.deleteSavedArticles());
    }
}
