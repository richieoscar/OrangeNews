package com.richieoscar.orangenews.model;

import java.net.URL;

public class Article {
    Source source;
    String author;
    String title;
    String description;
    String url;
    String urlToImage;
    String publishedAt;
    String content;

    public Source getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }
}
