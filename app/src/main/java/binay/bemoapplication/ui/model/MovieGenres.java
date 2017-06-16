package binay.bemoapplication.ui.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Binay on 15/06/17.
 */

public class MovieGenres {
    @SerializedName("id")
    private String mId;

    private String name;

    public MovieGenres() {
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ClassPojo [mId = " + mId + ", name = " + name + "]";
    }
}
