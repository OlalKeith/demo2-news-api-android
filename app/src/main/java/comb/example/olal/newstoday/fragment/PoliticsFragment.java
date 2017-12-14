package comb.example.olal.newstoday.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import comb.example.olal.newstoday.R;
import comb.example.olal.newstoday.SourcesAdapter;
import comb.example.olal.newstoday.api.NewsApiInterface;
import comb.example.olal.newstoday.model.Article;
import comb.example.olal.newstoday.model.JSONResponse;
import comb.example.olal.newstoday.model.PolitcsAdapter;
import comb.example.olal.newstoday.model.PoliticsJSONResponse;
import comb.example.olal.newstoday.model.Source;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by olal on 11/27/17.
 */

public class PoliticsFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Article> datalist = new ArrayList<Article>();
    private PolitcsAdapter adapter;

    public PoliticsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_politics, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.politics_card_recycler_view);
        loadJSON();

        return view;
    }

    private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NewsApiInterface apiInterface = retrofit.create(NewsApiInterface.class);
        Call<PoliticsJSONResponse> call =apiInterface.getPoliticsSources();

    call.enqueue(new Callback<PoliticsJSONResponse>() {
        @Override
        public void onResponse(Call<PoliticsJSONResponse> call, Response<PoliticsJSONResponse> response) {
            PoliticsJSONResponse jsonResponse = response.body();
            datalist = jsonResponse.getArticles();
            adapter = new PolitcsAdapter(getActivity(),datalist);
            RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(mlayoutManager);
            recyclerView.setAdapter(adapter);
        }

        @Override
        public void onFailure(Call<PoliticsJSONResponse> call, Throwable t) {

        }
    });

    }

}
