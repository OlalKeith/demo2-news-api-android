package comb.example.olal.newstoday.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;
import comb.example.olal.newstoday.R;
import comb.example.olal.newstoday.api.NewsApiInterface;
import comb.example.olal.newstoday.model.Article;
import comb.example.olal.newstoday.model.JSONResponse;
import comb.example.olal.newstoday.model.TechAdapter;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class TechFragment extends Fragment {

  private RecyclerView recyclerView;
  private List<Article> datalist = new ArrayList<>();
  private TechAdapter adapter;
  ProgressDialog pd;

  public TechFragment() {
    // Required empty public constructor
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_tech, container, false);

    pd = new ProgressDialog(getContext());
    pd.setMessage("Fetching News Data...");
    pd.setCancelable(false);
    pd.show();

    recyclerView = (RecyclerView) view.findViewById(R.id.tech_card_recycler_view);
    adapter = new TechAdapter(getContext(), datalist);
    loadJSON();
    return view;
  }


  private void loadJSON(){

    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://newsapi.org")
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    NewsApiInterface apiInterface = retrofit.create(NewsApiInterface.class);
    Call<JSONResponse> call = apiInterface.getTechSources();

    call.enqueue(new Callback<JSONResponse>() {
      @Override public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
        JSONResponse jsonResponse = response.body();
        datalist = jsonResponse.getArticles();
        adapter = new TechAdapter(getContext(), datalist);
        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mlayoutManager);
        recyclerView.setAdapter(adapter);
        pd.hide();
      }

      @Override public void onFailure(Call<JSONResponse> call, Throwable t) {
        Log.d("Error", t.getMessage());
        Toast.makeText(getContext(), "Error Fetching Data!", Toast.LENGTH_SHORT).show();

      }
    });


  }
}
