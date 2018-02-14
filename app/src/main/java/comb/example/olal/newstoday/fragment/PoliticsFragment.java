package comb.example.olal.newstoday.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import comb.example.olal.newstoday.R;
import comb.example.olal.newstoday.api.NewsApiInterface;
import comb.example.olal.newstoday.model.Article;
import comb.example.olal.newstoday.model.PoliticsAdapter;
import comb.example.olal.newstoday.model.JSONResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by olal on 11/27/17.
 */

public class PoliticsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

  private RecyclerView recyclerView;
  private List<Article> datalist = new ArrayList<Article>();
  private PoliticsAdapter adapter;
  ProgressDialog pd;
  private SwipeRefreshLayout swipeRefreshLayout;
  TextView Disconnected;
  TextView Link;
  private List<Article> articles;


  public PoliticsFragment() {
    // Required empty public constructor
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_politics, container, false);
    swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
    swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
    swipeRefreshLayout.setOnRefreshListener(this);

    swipeRefreshLayout.post(new Runnable() {
      @Override public void run() {

        swipeRefreshLayout.setRefreshing(true);
        loadJSON();

      }
    });


    pd = new ProgressDialog(getActivity());
    pd.setMessage("Fetching News Data...");
    pd.setCancelable(false);
    pd.show();

    recyclerView = (RecyclerView) view.findViewById(R.id.politics_card_recycler_view);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    recyclerView.smoothScrollToPosition(0);
    loadJSON();
    return view;
    //Link = (TextView)view.findViewById(R.id.link);
  }


  @Override public void onRefresh() {

    Toast.makeText(getActivity(), "Refreshing...", Toast.LENGTH_SHORT).show();
    loadJSON();
  }

  private void loadJSON() {
    //Disconnected = (TextView)recyclerView.findViewById(R.id.disconnected);
    swipeRefreshLayout.setRefreshing(true);
    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://newsapi.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    NewsApiInterface apiInterface = retrofit.create(NewsApiInterface.class);
    Call<JSONResponse> call = apiInterface.getPoliticsSources();

    call.enqueue(new Callback<JSONResponse>() {
      @Override public void onResponse(Call<JSONResponse> call,
          Response<JSONResponse> response) {
        JSONResponse jsonResponse = response.body();
        datalist = jsonResponse.getArticles();
        adapter = new PoliticsAdapter(getActivity(), datalist);
        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.smoothScrollToPosition(0);
        pd.hide();

        swipeRefreshLayout.setRefreshing(false);
      }

      @Override public void onFailure(Call<JSONResponse> call, Throwable t) {
        Log.d("Error", t.getMessage());
        Toast.makeText(getActivity(), "Error Fetching Data!", Toast.LENGTH_SHORT).show();
        //Disconnected.setVisibility(View.VISIBLE);
        pd.hide();

        swipeRefreshLayout.setRefreshing(false);

      }
    });
  }


}



/*DO I NEED THE TRY/CATCH HERE*/