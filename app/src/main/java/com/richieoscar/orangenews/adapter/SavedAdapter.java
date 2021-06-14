package com.richieoscar.orangenews.adapter;

import android.content.Intent;
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
import com.richieoscar.orangenews.model.SavedArticle;
import com.richieoscar.orangenews.repository.SavedArticleRepository;
import com.richieoscar.orangenews.ui.activities.DetailActivity;

import java.util.ArrayList;

public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.ArticleViewHolder> {

    private ArrayList<SavedArticle> articles;

    public SavedAdapter(ArrayList<SavedArticle> articles) {
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
        holder.like.setOnClickListener(v -> holder.remove(articles.get(position)));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    private void displayArticleInfo(int position, View v) {
        SavedArticle article = articles.get(position);
        Intent intent = new Intent(v.getContext(), DetailActivity.class);
        intent.putExtra("SavedArticle", article);
        v.getContext().startActivity(intent);
    }


    public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private ImageView like;
        private TextView title;
        private TextView publishedAt;
        private TextView source;
        private TextView time;
        private SavedArticleRepository repository = new SavedArticleRepository();

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

        private void bind(SavedArticle article) {
            Glide.with(itemView.getContext()).load(article.getUrlToImage()).into(imageView);
            title.setText(article.getTitle());
            source.setText(article.getSource().getName());
            time.setText(formatDate(article));
            like.setImageResource(R.drawable.ic_savebookmark);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            displayArticleInfo(position, v);
        }

        private void remove(SavedArticle article) {
            repository.deleteSavedArticle(article);
            Toast.makeText(repository.getContext(), "Removed", Toast.LENGTH_SHORT).show();
        }

        private String formatDate(SavedArticle article) {
            String format = article.getPublishedAt();
            return format.substring(0, 10);
        }
    }
}
