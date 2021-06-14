package com.richieoscar.orangenews.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.ui.activities.DetailActivity;

import java.util.ArrayList;

public class SourcesDetailAdapter extends RecyclerView.Adapter<SourcesDetailAdapter.ArticleViewHolder> {

    private ArrayList<Article> articles;

    public SourcesDetailAdapter( ArrayList<Article> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_list, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        holder.bind(articles.get(position));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    private void displayArticleInfo(int position, View v) {
        //Article article = sources.get(position);
        Intent intent = new Intent(v.getContext(), DetailActivity.class);
        // intent.putExtra("Article", article);
        v.getContext().startActivity(intent);
    }


    public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        ImageView like;
        TextView title;
        TextView publishedAt;
        TextView source;
        TextView description;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.list_title);
            source = itemView.findViewById(R.id.list_source);
            imageView = itemView.findViewById(R.id.list_image);
            like = itemView.findViewById(R.id.list_like);
            description = itemView.findViewById(R.id.description);
            publishedAt = itemView.findViewById(R.id.publishDetail);
            itemView.setOnClickListener(this);
        }

        public void bind(Article article) {
            Glide.with(itemView.getContext()).load(article.getUrlToImage()).into(imageView);
            title.setText(article.getTitle());
            description.setText(article.getDescription());
            source.setText(article.getSource().getName());
        }

        @Override
        public void onClick(View v) {
        }

    }
}
