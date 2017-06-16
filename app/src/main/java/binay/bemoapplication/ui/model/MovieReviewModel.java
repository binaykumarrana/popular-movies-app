package binay.bemoapplication.ui.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Binay on 15/06/17.
 */

public class MovieReviewModel {
    public MovieReviewModel() {
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    @SerializedName("id")
    private String mId;
    @SerializedName("results")
    private List<MovieReviewResults> results;

    private String page;

    private String total_pages;

    private String total_results;


    public List<MovieReviewResults> getResults() {
        return results;
    }

    public void setResults(List<MovieReviewResults> results) {
        this.results = results;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }

    public String getTotal_results() {
        return total_results;
    }

    public void setTotal_results(String total_results) {
        this.total_results = total_results;
    }

    @Override
    public String toString() {
        return "ClassPojo [mId = " + mId + ", results = " + results + ", page = " + page + ", total_pages = " + total_pages + ", total_results = " + total_results + "]";
    }
}
