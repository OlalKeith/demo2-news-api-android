package comb.example.olal.newstoday;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by olal on 9/5/17.
 */

//ViewHolder IS USED TO SPEED UP RENDERING OF YOUR LISTVIEW

public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.ViewHolder> {

    private ArrayList<Source>sources;

    public SourcesAdapter(ArrayList<Source>sources){

        this.sources = sources;
    }


    @Override
    public SourcesAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sources_list,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SourcesAdapter.ViewHolder viewHolder, int position) {

        viewHolder.sourcesName.setText(sources.get(position).getName());
        viewHolder.sourcesDescription.setText(sources.get(position).getDescription());
        viewHolder.sourcesCategory.setText(sources.get(position).getCategory());

    }

    @Override
    public int getItemCount() {
        return sources.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView sourcesName,sourcesDescription,sourcesCategory;


        public ViewHolder(View itemView) {
            super(itemView);

            sourcesName = (TextView)itemView.findViewById(R.id.sources_name);
            sourcesDescription = (TextView)itemView.findViewById(R.id.sources_description);
            sourcesCategory = (TextView)itemView.findViewById(R.id.sources_category);
        }
    }
}
