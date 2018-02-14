package comb.example.olal.newstoday.model;

import android.content.Context;
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
import comb.example.olal.newstoday.R;
import java.util.List;

/**
 * Created by olal on 2/14/18.
 */

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.ViewHolder> {

  public Context mContext;
  private List<Article> articles;

  public SportAdapter(Context mContext, List<Article> articles){

    this.articles = articles;
    this.mContext = mContext;
  }


  @Override public SportAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

    View view = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.sport_card, viewGroup, false);
    return new ViewHolder(view);

  }

  @Override public void onBindViewHolder(SportAdapter.ViewHolder viewHolder, int position) {
    final Article article = articles.get(position);
    viewHolder.sourcesTitle.setText(article.getTitle());
    viewHolder.sourcesName.setText(article.getSource().getName());
    viewHolder.sourcesDescription.setText(article.getDescription());
    viewHolder.sourcesPublishedAt.setText(article.getPublishedAt());
    Picasso.with(mContext).load(article.getUrlToImage()).into(viewHolder.sourcesImage);

    viewHolder.main.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(article.getUrl()));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
      }
    });

  }

  @Override public int getItemCount() {
    return articles.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder{

    private TextView sourcesName, sourcesDescription, sourcesTitle, sourcesPublishedAt;
    private ImageView sourcesImage;
    private LinearLayout main;

    public ViewHolder(View itemView) {
      super(itemView);

      sourcesImage = (ImageView) itemView.findViewById(R.id.image_url);
      sourcesTitle = (TextView) itemView.findViewById(R.id.sources_title);
      sourcesName = (TextView) itemView.findViewById(R.id.sources_name);
      sourcesDescription = (TextView) itemView.findViewById(R.id.sources_description);
      sourcesPublishedAt = (TextView) itemView.findViewById(R.id.sources_publishedAt);
      main = (LinearLayout) itemView.findViewById(R.id.main);
    }
  }
}
