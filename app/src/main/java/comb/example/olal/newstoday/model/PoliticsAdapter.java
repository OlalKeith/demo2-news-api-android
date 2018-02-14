package comb.example.olal.newstoday.model;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.util.List;

import comb.example.olal.newstoday.R;

/**
 * Created by olal on 12/13/17.
 */

public class PoliticsAdapter extends RecyclerView.Adapter<PoliticsAdapter.ViewHolder> {

  public Activity activity;
  private List<Article> articles;

  public PoliticsAdapter(Activity activity, List<Article> articles) {

    this.articles = articles;
    this.activity = activity;
  }

  @Override public PoliticsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
    View view = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.politics_card, viewGroup, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(PoliticsAdapter.ViewHolder viewHolder, int position) {
    final Article article = articles.get(position);
    viewHolder.sourcesTitle.setText(article.getTitle());
    viewHolder.sourcesName.setText(article.getSource().getName());
    viewHolder.sourcesDescription.setText(article.getDescription());
    viewHolder.sourcesPublishedAt.setText(article.getPublishedAt());
    Picasso.with(activity).load(article.getUrlToImage()).into(viewHolder.sourcesImage);
    viewHolder.main.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
      }
    });

  }

  @Override public int getItemCount() {
    return articles.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    private TextView sourcesName, sourcesDescription, sourcesPublishedAt, sourcesTitle, sourceUrl;
    private ImageView sourcesImage;
    private LinearLayout main;

    public ViewHolder(View itemView) {
      super(itemView);
      sourcesImage = (ImageView) itemView.findViewById(R.id.image_url);
      sourcesTitle = (TextView) itemView.findViewById(R.id.sources_title);
      sourcesName = (TextView) itemView.findViewById(R.id.sources_name);
      sourcesDescription = (TextView) itemView.findViewById(R.id.sources_description);
      sourcesPublishedAt = (TextView) itemView.findViewById(R.id.sources_publishedAt);;
      main = (LinearLayout) itemView.findViewById(R.id.main);




    }
  }


}
