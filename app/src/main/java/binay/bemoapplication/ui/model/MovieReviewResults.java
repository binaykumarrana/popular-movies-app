package binay.bemoapplication.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Binay on 15/06/17.
 */

public class MovieReviewResults implements Parcelable{
    private String content;

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    @SerializedName("id")
    private String mId;

    private String author;

    private String url;

    public MovieReviewResults() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public void setId(String id) {
        this.mId = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ClassPojo [content = " + content + ", mId = " + mId + ", author = " + author + ", url = " + url + "]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(content);
        dest.writeString(mId);
        dest.writeString(author);
        dest.writeString(url);
    }
}
