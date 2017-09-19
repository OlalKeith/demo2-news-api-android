package comb.example.olal.newstoday;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class SourcesFragment extends Fragment {


    private RecyclerView recyclerView;
    private ArrayList<Source> data;
    private SourcesAdapter adapter;


    public SourcesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        initViews();
        // Inflate the layout for this fragment
       View rootView = inflater.inflate(R.layout.fragment_sources, container, false);



        recyclerView = (RecyclerView) rootView.findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        loadJSON();

        return  rootView;

    }

    private void loadJSON(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        NewsApiInterface apiInterface = retrofit.create(NewsApiInterface.class);
        Call<SourcesResponse> call = apiInterface.getTechSources();

        call.enqueue(new Callback<SourcesResponse>() {
            @Override
            public void onResponse(Call<SourcesResponse> call, Response<SourcesResponse> response) {
                SourcesResponse sourcesResponse = response.body();
                data = new ArrayList<Source>(Arrays.asList(sourcesResponse.getSources()));
                adapter = new SourcesAdapter(data);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<SourcesResponse> call, Throwable t) {

                Log.d("Error",t.getMessage());

            }
        });

    }

}
