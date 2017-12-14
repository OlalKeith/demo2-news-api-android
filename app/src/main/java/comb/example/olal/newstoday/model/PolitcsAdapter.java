package comb.example.olal.newstoday.model;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import comb.example.olal.newstoday.R;
import comb.example.olal.newstoday.SourcesAdapter;

/**
 * Created by olal on 12/13/17.
 */

public class PolitcsAdapter extends RecyclerView.Adapter<PolitcsAdapter.ViewHolder> {

    public Activity activity;
    private List<Article> articles;

    public PolitcsAdapter(Activity activity, List<Article> articles){

        this.articles = articles;
        this.activity = activity;
    }


    @Override
    public PolitcsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.politics_card,viewGroup,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(PolitcsAdapter.ViewHolder viewHolder, int position) {
Article article = articles.get(position);
        viewHolder.sourcesName.setText(article.getPoliticssource().getName());
        viewHolder.sourcesDescription.setText(article.getDescription());
        viewHolder.sourcesPublishedAt.setText(article.getPublishedAt());

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView sourcesName,sourcesDescription,sourcesPublishedAt;


        public ViewHolder(View itemView) {
            super(itemView);

            sourcesName = (TextView)itemView.findViewById(R.id.sources_name);
            sourcesDescription = (TextView)itemView.findViewById(R.id.sources_description);
            sourcesPublishedAt = (TextView)itemView.findViewById(R.id.sources_publishedAt);
        }
    }
}
