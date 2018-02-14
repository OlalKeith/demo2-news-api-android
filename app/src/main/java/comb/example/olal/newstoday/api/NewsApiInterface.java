package comb.example.olal.newstoday.api;

import comb.example.olal.newstoday.model.JSONResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by olal on 9/5/17.
 */

public interface NewsApiInterface {

  @GET("/v2/top-headlines?country=us&category=business&apiKey=f1e6b2116c774240b3f3962e89a00817")
  Call<JSONResponse> getbusinessSources();

  @GET("/v2/everything?sources=breitbart-news,politico,the-hill&apiKey=f1e6b2116c774240b3f3962e89a00817")
  Call<JSONResponse> getPoliticsSources();

  @GET("/v2/top-headlines?country=gb&category=technology&apiKey=f1e6b2116c774240b3f3962e89a00817")
  Call<JSONResponse> getTechSources();

  @GET("/v2/top-headlines?country=ng&category=sport&apiKey=f1e6b2116c774240b3f3962e89a00817")
  Call<JSONResponse> getSportSources();

  @GET("/v2/top-headlines?country=us&category=general&apiKey=f1e6b2116c774240b3f3962e89a00817")
  Call<JSONResponse> getGeneralSources();


}
