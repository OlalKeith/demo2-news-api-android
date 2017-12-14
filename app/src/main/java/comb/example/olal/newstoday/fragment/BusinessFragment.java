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

import comb.example.olal.newstoday.api.NewsApiInterface;
import comb.example.olal.newstoday.R;
import comb.example.olal.newstoday.model.Source;
import comb.example.olal.newstoday.SourcesAdapter;
import comb.example.olal.newstoday.model.JSONResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BusinessFragment extends Fragment {

private RecyclerView recyclerView;
private List<Source> datalist = new ArrayList<Source>();
private SourcesAdapter adapter;

    public BusinessFragment() {
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
        View view = inflater.inflate(R.layout.fragment_business, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.news_card_recycler_view);
        loadJSON();
        adapter = new SourcesAdapter(getActivity(), datalist);
        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }
        private void loadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NewsApiInterface apiInterface = retrofit.create(NewsApiInterface.class);
        Call<JSONResponse> call = apiInterface.getTechSources();

        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();
                datalist = jsonResponse.getSources();
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error",t.getMessage());

            }
        });

    }






}
