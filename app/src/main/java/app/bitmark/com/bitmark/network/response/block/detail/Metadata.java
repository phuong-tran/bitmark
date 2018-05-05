package app.bitmark.com.bitmark.network.response.block.detail;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metadata implements Serializable, Parcelable {
    @SerializedName("Created")
    @Expose
    private String created;
    @SerializedName("Creator")
    @Expose
    private String creator;

    @SerializedName("Created (date)")
    @Expose
    private String createdDate;

    public final static Parcelable.Creator<Metadata> CREATOR = new Creator<Metadata>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Metadata createFromParcel(Parcel in) {
            return new Metadata(in);
        }

        public Metadata[] newArray(int size) {
            return (new Metadata[size]);
        }

    }
            ;
    private final static long serialVersionUID = -8320081815521207L;

    protected Metadata(Parcel in) {
        this.created = ((String) in.readValue((String.class.getClassLoader())));
        this.creator = ((String) in.readValue((String.class.getClassLoader())));
        this.createdDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Metadata() {
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(created);
        dest.writeValue(creator);
        dest.writeValue(createdDate);
    }

    public int describeContents() {
        return 0;
    }

}