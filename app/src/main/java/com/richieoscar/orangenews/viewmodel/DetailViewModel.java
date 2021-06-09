package com.richieoscar.orangenews.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.richieoscar.orangenews.model.SavedArticle;
import com.richieoscar.orangenews.repository.SavedArticleRepository;

public class DetailViewModel extends AndroidViewModel {
    private String title;
    private String author;
    private String source;
    private String imageUrl;
    private String published;
    private String content;
    private SavedArticleRepository repository;

    public DetailViewModel(@NonNull Application application) {
        super(application);
        repository = new SavedArticleRepository(application);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void saveArticle(SavedArticle article) {
        repository.saveArticle(article);
    }
}
