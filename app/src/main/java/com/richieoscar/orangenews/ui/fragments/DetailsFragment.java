package com.richieoscar.orangenews.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ShareCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.databinding.FragmentDetailsBinding;
import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.model.SavedArticle;
import com.richieoscar.orangenews.ui.activities.MainActivity;
import com.richieoscar.orangenews.viewmodel.DetailViewModel;

public class DetailsFragment extends Fragment {

    private FragmentDetailsBinding binding;
    private DetailViewModel viewModel;
    private Article article;
    private SavedArticle saved;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MainActivity activity = (MainActivity) getActivity();
        activity.getSupportActionBar().show();
        activity.hideBottomNavigation();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false);
        viewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        article = getArguments().getParcelable(getString(R.string.article));
        saved = getArguments().getParcelable("SavedArticle");

        display(article);
        displaySavedArticle(saved);
        launchUrl();
        return binding.getRoot();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                MainActivity activity = (MainActivity) getActivity();
                activity.onBackPressed();
                return true;
            case R.id.share:
                shareArticle();
                return true;
            case R.id.add_to_bookmark:
                addToBookMark();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.search).setVisible(false);
        menu.findItem(R.id.add_to_bookmark).setVisible(true);
        menu.findItem(R.id.share).setVisible(true);
    }

    private void launchUrl() {
        binding.buttonRead.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString(getString(R.string.artcle_url), article.getUrl());
            MainActivity activity = (MainActivity) v.getContext();
            activity.openWebView(bundle);
        });
    }

    private void display(Article article) {
        if (article != null) {

            viewModel.setAuthor(article.getAuthor());
            viewModel.setContent(article.getContent());
            viewModel.setTitle(article.getTitle());
            viewModel.setImageResource(article.getImageRes());
            viewModel.setSource(article.getSource().getName());
            if (article.getUrlToImage() == null) {
                viewModel.setImageResource(R.drawable.logo);
                Glide.with(getContext()).load(viewModel.getImageResource()).into(binding.imageDetail);
            } else {
                viewModel.setImageUrl(article.getUrlToImage());
                Glide.with(getContext()).load(viewModel.getImageUrl()).into(binding.imageDetail);
            }
            viewModel.setPublished(article.getPublishedAt());
            binding.titleDetail.setText(viewModel.getTitle());
            binding.authorDetail.setText(viewModel.getAuthor());
            binding.publishDetail.setText(formatDate(viewModel.getPublished()));
            binding.contentDetail.setText(viewModel.getContent());
            binding.source.setText(viewModel.getSource());

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
        Toast.makeText(getContext(), R.string.save_article, Toast.LENGTH_SHORT).show();
    }

    private void shareArticle() {
        ShareCompat.IntentBuilder.from(getActivity())
                .setType("text/plain")
                .setChooserTitle(R.string.share_with)
                .setText(article.getTitle() + "\n"
                        + article.getUrl() + "\n" + getString(R.string.app_name_tag))
                .startChooser();
    }

    private String formatDate(String date) {
        return date.substring(0, 10);
    }
}