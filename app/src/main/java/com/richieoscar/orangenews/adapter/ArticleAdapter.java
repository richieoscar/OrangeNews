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
import com.richieoscar.orangenews.ui.DetailActivity;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private ArrayList<Article> articles;

    public ArticleAdapter(ArrayList<Article> articles) {
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
        Article article = articles.get(position);
        Intent intent = new Intent(v.getContext(), DetailActivity.class);
        intent.putExtra("Article", article);
        v.getContext().startActivity(intent);
    }


    public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        ImageView share;
        TextView title;
        TextView source;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.list_title);
            source = itemView.findViewById(R.id.list_source);
            imageView = itemView.findViewById(R.id.list_image);
            share = itemView.findViewById(R.id.list_share);
            itemView.setOnClickListener(this);
        }

        public void bind(Article article) {
            title.setText(article.getTitle());
            source.setText(article.getSource().getName());
            Glide.with(itemView.getContext()).load(article.getImageUrl()).into(imageView);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            displayArticleInfo(position, v);
        }
    }
}
