package comb.example.olal.newstoday;

import com.google.gson.annotations.SerializedName;

/**
 * Created by olal on 9/5/17.
 */

public class Source {

//    @SerializedName("source")
    private String name;
    private String description;
    private String category;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }
}
