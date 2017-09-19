package comb.example.olal.newstoday;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by olal on 9/5/17.
 */

public interface NewsApiInterface {

    @GET("/v1/sources?language=en&category=technology")
    Call<SourcesResponse> getTechSources();
}
