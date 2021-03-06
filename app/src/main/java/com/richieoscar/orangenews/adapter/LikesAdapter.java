package com.richieoscar.orangenews.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.repository.LikesRepository;
import com.richieoscar.orangenews.ui.activities.MainActivity;

import java.util.ArrayList;

public class LikesAdapter extends RecyclerView.Adapter<LikesAdapter.ArticleViewHolder> {

    private ArrayList<Article> articles;

    public LikesAdapter(ArrayList<Article> articles) {
        this.articles = articles;
    }


    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.like_list, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        holder.bind(articles.get(position));
        holder.like.setOnClickListener(v -> holder.liked(articles.get(position)));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    private void displayArticleInfo(int position, View v) {
        Article article = articles.get(position);
        Bundle bundle = new Bundle();
        bundle.putParcelable("Article", article);
        MainActivity activity = (MainActivity) v.getContext();
        activity.openDetail(bundle);
    }


    public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private ImageView like;
        private TextView title;
        private TextView publishedAt;
        private TextView source;
        private TextView time;
        private LikesRepository repository = new LikesRepository();

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.list_title);
            source = itemView.findViewById(R.id.list_source);
            imageView = itemView.findViewById(R.id.list_image);
            like = itemView.findViewById(R.id.list_like);
            publishedAt = itemView.findViewById(R.id.publishDetail);
            time = itemView.findViewById(R.id.list_time);
            itemView.setOnClickListener(this);
            like.setOnClickListener(this);
            repository.setContext(itemView.getContext());
        }


        private void bind(Article article) {
            Glide.with(itemView.getContext()).load(article.getUrlToImage()).into(imageView);
            title.setText(article.getTitle());
            source.setText(article.getSource().getName());
            time.setText(formatDate(article));
            like.setImageResource(R.drawable.ic_like_filled);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            displayArticleInfo(position, v);
        }

        private void liked(Article article) {
            repository.removeFromLikes(article);
            Toast.makeText(repository.getContext(), "Removed from likes", Toast.LENGTH_SHORT).show();
        }

        private String formatDate(Article article) {
            String format = article.getPublishedAt();
            return format.substring(0, 10);
        }
    }
}
