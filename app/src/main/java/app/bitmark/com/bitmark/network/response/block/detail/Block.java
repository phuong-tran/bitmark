package app.bitmark.com.bitmark.network.response.block.detail;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Block implements Serializable, Parcelable {
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    public final static Parcelable.Creator<Block> CREATOR = new Creator<Block>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Block createFromParcel(Parcel in) {
            return new Block(in);
        }

        public Block[] newArray(int size) {
            return (new Block[size]);
        }

    }
            ;
    private final static long serialVersionUID = 5547869161602280967L;

    protected Block(Parcel in) {
        this.number = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.hash = ((String) in.readValue((String.class.getClassLoader())));
        this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Block() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(number);
        dest.writeValue(hash);
        dest.writeValue(createdAt);
    }

    public int describeContents() {
        return 0;
    }

}