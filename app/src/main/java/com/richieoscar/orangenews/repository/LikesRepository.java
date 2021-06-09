package com.richieoscar.orangenews.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.richieoscar.orangenews.db.OrangeDao;
import com.richieoscar.orangenews.db.OrangeNewsDatabase;
import com.richieoscar.orangenews.model.Article;

import java.util.List;

public class LikesRepository {
    Context context;
    OrangeDao dao;
    OrangeNewsDatabase database;

    public LikesRepository(Context context) {
        database = OrangeNewsDatabase.getOrangeNewsDatabaseInstance(context.getApplicationContext());
        dao = database.orangeDao();
    }

    public LikesRepository() {
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
        database = OrangeNewsDatabase.getOrangeNewsDatabaseInstance(context);
        dao = database.orangeDao();
    }

    public void addToLikes(Article article) {
        OrangeNewsDatabase.doInBackground.execute(() -> dao.addToLikes(article));
    }

    public LiveData<List<Article>> getLikes() {
        return dao.getLikes();
    }

    public void removeFromLikes(Article article) {
        OrangeNewsDatabase.doInBackground.execute(() -> dao.removeFromLikes(article));
    }

    public void clearLikes() {
        OrangeNewsDatabase.doInBackground.execute(() -> dao.clearLikes());
    }
}
