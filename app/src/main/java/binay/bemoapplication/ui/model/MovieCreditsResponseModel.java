package binay.bemoapplication.ui.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Binay on 15/06/17.
 */

public class MovieCreditsResponseModel {
    @SerializedName("id")
    private String mId;

    @SerializedName("cast")
    private List<MovieCreditsCastModel> cast;

    @SerializedName("crew")
    private List<MovieCreditsCrewModel> crew;

    public MovieCreditsResponseModel() {
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public List<MovieCreditsCastModel> getCast() {
        return cast;
    }

    public void setCast(List<MovieCreditsCastModel> cast) {
        this.cast = cast;
    }

    public List<MovieCreditsCrewModel> getCrew() {
        return crew;
    }

    public void setCrew(List<MovieCreditsCrewModel> crew) {
        this.crew = crew;
    }

    @Override
    public String toString() {
        return "ClassPojo [mId = " + mId + ", cast = " + cast + ", crew = " + crew + "]";
    }
}
