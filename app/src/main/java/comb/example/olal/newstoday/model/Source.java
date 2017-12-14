package comb.example.olal.newstoday.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by olal on 9/5/17.
 */

public class Source {

//    @SerializedName("source")
    private String name;
    private String description;
    private String category;
    private String url;
    private String urlToImage;
    private String publishedAt;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getUrl(){
        return url;
    }

    public String getUrlToImage(){
        return urlToImage;
    }


    public String getPublishedAt(){
        return publishedAt;
    }
}
