package com.richieoscar.orangenews.model;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;

public class JsonResult {
    String status;
    int totalResults;
    private ArrayList<Article> articles;

    public String getStatus() {
        return status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }
}
