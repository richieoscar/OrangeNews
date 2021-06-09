package com.richieoscar.orangenews.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.databinding.ActivityDetailBinding;
import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.model.SavedArticle;
import com.richieoscar.orangenews.viewmodel.DetailViewModel;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private DetailViewModel viewModel;
    private Article article;
    private SavedArticle savedArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);
        viewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        article = getIntent().getParcelableExtra(getString(R.string.article));
        savedArticle = getIntent().getParcelableExtra(getString(R.string.saved_article));
        display(article);
        displaySavedArticle(savedArticle);
        readFullArticle();
    }

    private void readFullArticle() {
        binding.buttonRead.setOnClickListener(v -> {
            Intent readIntent = new Intent(this, WebActivity.class);
            readIntent.putExtra("NewsUrl", article.getUrl());
            startActivity(readIntent);
        });
    }

    private void display(Article article) {
        if (article != null) {
            viewModel.setAuthor(article.getAuthor());
            viewModel.setContent(article.getContent());
            viewModel.setTitle(article.getTitle());
            viewModel.setSource(article.getSource().getName());
            viewModel.setImageUrl(article.getUrlToImage());
            viewModel.setPublished(article.getPublishedAt());
            binding.titleDetail.setText(viewModel.getTitle());
            binding.authorDetail.setText(viewModel.getAuthor());
            binding.publishDetail.setText(formatDate(viewModel.getPublished()));
            binding.contentDetail.setText(viewModel.getContent());
            binding.source.setText(viewModel.getSource());
            Glide.with(this).load(viewModel.getImageUrl()).into(binding.imageDetail);

        }
    }

    private void displaySavedArticle(SavedArticle article) {
        if (article != null) {
            viewModel.setAuthor(article.getAuthor());
            viewModel.setContent(article.getContent());
            viewModel.setTitle(article.getTitle());
            viewModel.setSource(article.getSource().getName());
            viewModel.setImageUrl(article.getUrlToImage());
            viewModel.setPublished(article.getPublishedAt());
            binding.titleDetail.setText(viewModel.getTitle());
            binding.authorDetail.setText(viewModel.getAuthor());
            binding.publishDetail.setText(formatDate(viewModel.getPublished()));
            binding.contentDetail.setText(viewModel.getContent());
            binding.source.setText(viewModel.getSource());
            Glide.with(this).load(viewModel.getImageUrl()).into(binding.imageDetail);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.share:
                shareArticle();
                break;
            case R.id.add_to_bookmark:
                addToBookMark();
                break;
            default:
                //do nothing
        }
        return super.onOptionsItemSelected(item);
    }

    private void addToBookMark() {
        SavedArticle savedArticle = new SavedArticle();
        savedArticle.setAuthor(article.getAuthor());
        savedArticle.setContent(article.getContent());
        savedArticle.setDescription(article.getDescription());
        savedArticle.setPublishedAt(article.getPublishedAt());
        savedArticle.setTitle(article.getTitle());
        savedArticle.setSource(article.getSource());
        savedArticle.setUrl(article.getUrl());
        savedArticle.setUrlToImage(article.getUrlToImage());
        savedArticle.setImageRes(article.getImageRes());
        viewModel.saveArticle(savedArticle);
        Toast.makeText(this, R.string.save_article, Toast.LENGTH_SHORT).show();
    }

    private void shareArticle() {
        ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setChooserTitle(R.string.share_with)
                .setText(article.getTitle() + "\n"
                        + article.getUrl() + "\n" + getString(R.string.app_name_tag))
                .startChooser();
    }

    private String formatDate(String date) {
        return date.substring(0,10);

    }
}