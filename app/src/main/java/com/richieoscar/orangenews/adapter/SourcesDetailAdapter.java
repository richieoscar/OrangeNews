package com.richieoscar.orangenews.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.model.Article;
import com.richieoscar.orangenews.model.Source;
import com.richieoscar.orangenews.repository.SourcesRepository;
import com.richieoscar.orangenews.ui.DetailActivity;
import com.richieoscar.orangenews.ui.MainActivity;

import java.util.ArrayList;

public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.ArticleViewHolder> {

    private ArrayList<Source> sources;
    private ArrayList<Article> articles;
    private SourcesRepository repository = new SourcesRepository();

    public SourcesAdapter(ArrayList<Source> sources, ArrayList<Article> articles) {
        this.sources = sources;
        this.articles = articles;
    }


    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.sources_list, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        holder.bind(sources.get(position));
    }

    @Override
    public int getItemCount() {
        return sources.size();
    }

    private void displayArticleInfo(int position, View v) {
        //Article article = sources.get(position);
        Intent intent = new Intent(v.getContext(), DetailActivity.class);
        // intent.putExtra("Article", article);
        v.getContext().startActivity(intent);
    }


    public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView description;
        TextView category;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.source_name);
            category = itemView.findViewById(R.id.source_category);
            description = itemView.findViewById(R.id.source_description);
            itemView.setOnClickListener(this);
        }

        public void bind(Source source) {
            title.setText(source.getName());
            description.setText(source.getDescription());
            category.setText(source.getCategory());

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Source source = sources.get(position);
            String domain = getDomain(source.getUrl());
            repository.setDomain(domain);
            MainActivity activity = (MainActivity) v.getContext();
            activity.navigateToSourceDetail();
        }

        public String getDomain(String url) {
            String[] domain = url.split("\\.");
            return domain[1] + "." + domain[2];
        }

    }
}
