package com.richieoscar.orangenews.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.richieoscar.orangenews.R;
import com.richieoscar.orangenews.model.Source;
import com.richieoscar.orangenews.ui.activities.MainActivity;

import java.util.ArrayList;

public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.ArticleViewHolder> {

    private ArrayList<Source> sources;

    public SourcesAdapter(ArrayList<Source> sources) {
        this.sources = sources;
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


    public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView description;
        TextView category;
        TextView country;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.source_name);
            category = itemView.findViewById(R.id.source_category);
            description = itemView.findViewById(R.id.source_description);
            country = itemView.findViewById(R.id.sources_country);
            itemView.setOnClickListener(this);
        }

        public void bind(Source source) {
            title.setText(source.getName());
            description.setText(source.getDescription());
            category.setText(format(source.getCategory()));
            country.setText(format(source.getCountry() + " | "));
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Source source = sources.get(position);
            String domain = source.getUrl();
            Bundle bundle = new Bundle();
            bundle.putString("Url", domain);
            bundle.putString("Name", source.getName());
            MainActivity activity = (MainActivity) v.getContext();
            activity.navigateToSourceDetail(bundle);
        }
    }

    private String format(String word) {
        if (word != null) {
            String first = String.valueOf(word.charAt(0));
            String firstLetterUpper = String.valueOf(word.charAt(0)).toUpperCase();
            return word.replaceFirst(first, firstLetterUpper);
        } else return "";
    }
}
