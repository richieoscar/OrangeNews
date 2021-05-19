package com.richieoscar.orangenews.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
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
import com.richieoscar.orangenews.ui.MainActivity;

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
        holder.like.setOnClickListener(v -> holder.liked(articles.get(position)));
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
            like.setOnClickListener(this);
        }

        public void bind(Article article) {
            Glide.with(itemView.getContext()).load(article.getImageUrl()).into(imageView);
            title.setText(article.getTitle());
            description.setText(article.getDescription());
            source.setText(article.getSource().getName());
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            displayArticleInfo(position, v);
        }

        public void liked(Article article){
            if (article != null) like.setImageResource(R.drawable.ic_like_filled);
        }
    }
}
