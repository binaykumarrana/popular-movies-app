package binay.bemoapplication.ui.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Binay on 15/06/17.
 */

public class MovieVideoResponseModel {
    @SerializedName("id")
    private String mId;

    public MovieVideoResponseModel() {
    }

    @SerializedName("results")
    private List<MovieVideoResultModel> results;

    public String getmId() {
        return mId;
    }

    public void setId(String id) {
        this.mId = id;
    }

    public List<MovieVideoResultModel> getResults() {
        return results;
    }

    public void setResults(List<MovieVideoResultModel> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ClassPojo [mId = " + mId + ", results = " + results + "]";
    }
}
