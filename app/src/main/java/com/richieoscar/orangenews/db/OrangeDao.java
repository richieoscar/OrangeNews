package com.richieoscar.orangenews.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.model.SavedArticle;

import java.util.List;

@Dao
public interface OrangeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addToLikes(Article article);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void saveArticle(SavedArticle article);

    @Query("SELECT *FROM likes")
    LiveData<List<Article>> getLikes();

    @Query("SELECT *FROM saved_articles")
    LiveData<List<SavedArticle>> getSavedArticles();

    @Delete
    void removeFromLikes(Article article);

    @Delete
    void deleteSavedArticle(SavedArticle article);

    @Query("DELETE FROM likes")
    void clearLikes();

    @Query("DELETE FROM saved_articles")
    void deleteSavedArticles();
}

