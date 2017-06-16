package binay.bemoapplication.ui.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Binay on 15/06/17.
 */

public class MovieResponseModel {
    @SerializedName("results")
    private List<MovieResultsModel> results;

    private String page;

    private String total_pages;

    private String total_results;

    public MovieResponseModel() {
    }

    public List<MovieResultsModel> getResults() {
        return results;
    }

    public void setResults(List<MovieResultsModel> results) {
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
        return "ClassPojo [results = " + results + ", page = " + page + ", total_pages = " + total_pages + ", total_results = " + total_results + "]";
    }



}
