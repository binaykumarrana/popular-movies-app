package binay.bemoapplication.ui.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Binay on 15/06/17.
 */

public class MovieCreditsCrewModel {
    @SerializedName("id")
    private String mId;

    private String credit_id;

    private String department;

    private String name;

    private String job;

    private String profile_path;

    public MovieCreditsCrewModel() {
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getCredit_id() {
        return credit_id;
    }

    public void setCredit_id(String credit_id) {
        this.credit_id = credit_id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    @Override
    public String toString() {
        return "ClassPojo [mId = " + mId + ", credit_id = " + credit_id + ", department = " + department + ", name = " + name + ", job = " + job + ", profile_path = " + profile_path + "]";
    }
}
