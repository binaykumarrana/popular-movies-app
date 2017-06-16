package binay.bemoapplication.ui.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Binay on 15/06/17.
 */

public class MovieCreditsCastModel {
    @SerializedName("id")
    private String mId;

    @SerializedName("order")
    private String mOrder;

    private String credit_id;

    private String name;

    private String cast_id;

    private String profile_path;

    private String character;

    public MovieCreditsCastModel() {
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmOrder() {
        return mOrder;
    }

    public void setmOrder(String mOrder) {
        this.mOrder = mOrder;
    }

    public String getCredit_id() {
        return credit_id;
    }

    public void setCredit_id(String credit_id) {
        this.credit_id = credit_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCast_id() {
        return cast_id;
    }

    public void setCast_id(String cast_id) {
        this.cast_id = cast_id;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    @Override
    public String toString() {
        return "ClassPojo [mId = " + mId + ", mOrder = " + mOrder + ", credit_id = " + credit_id + ", name = " + name + ", cast_id = " + cast_id + ", profile_path = " + profile_path + ", character = " + character + "]";
    }
}
