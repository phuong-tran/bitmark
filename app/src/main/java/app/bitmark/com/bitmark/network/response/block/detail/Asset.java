package app.bitmark.com.bitmark.network.response.block.detail;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Asset implements Serializable, Parcelable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("fingerprint")
    @Expose
    private String fingerprint;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;
    @SerializedName("registrant")
    @Expose
    private String registrant;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("block_number")
    @Expose
    private Integer blockNumber;
    @SerializedName("block_offset")
    @Expose
    private Integer blockOffset;
    @SerializedName("expires_at")
    @Expose
    private Object expiresAt;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    public final static Parcelable.Creator<Asset> CREATOR = new Creator<Asset>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Asset createFromParcel(Parcel in) {
            return new Asset(in);
        }

        public Asset[] newArray(int size) {
            return (new Asset[size]);
        }

    }
            ;
    private final static long serialVersionUID = -5131521124856595155L;

    protected Asset(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.fingerprint = ((String) in.readValue((String.class.getClassLoader())));
        this.metadata = ((Metadata) in.readValue((Metadata.class.getClassLoader())));
        this.registrant = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.blockNumber = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.blockOffset = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.expiresAt = ((Object) in.readValue((Object.class.getClassLoader())));
        this.offset = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Asset() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public String getRegistrant() {
        return registrant;
    }

    public void setRegistrant(String registrant) {
        this.registrant = registrant;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(Integer blockNumber) {
        this.blockNumber = blockNumber;
    }

    public Integer getBlockOffset() {
        return blockOffset;
    }

    public void setBlockOffset(Integer blockOffset) {
        this.blockOffset = blockOffset;
    }

    public Object getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Object expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(fingerprint);
        dest.writeValue(metadata);
        dest.writeValue(registrant);
        dest.writeValue(status);
        dest.writeValue(blockNumber);
        dest.writeValue(blockOffset);
        dest.writeValue(expiresAt);
        dest.writeValue(offset);
    }

    public int describeContents() {
        return 0;
    }

}