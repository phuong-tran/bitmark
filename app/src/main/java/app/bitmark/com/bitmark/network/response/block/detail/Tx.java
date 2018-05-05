package app.bitmark.com.bitmark.network.response.block.detail;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tx implements Serializable, Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("owner")
    @Expose
    private String owner;
    @SerializedName("asset_id")
    @Expose
    private String assetId;
    @SerializedName("head")
    @Expose
    private String head;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("block_number")
    @Expose
    private Integer blockNumber;
    @SerializedName("block_offset")
    @Expose
    private Integer blockOffset;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("expires_at")
    @Expose
    private Object expiresAt;
    @SerializedName("pay_id")
    @Expose
    private String payId;
    @SerializedName("previous_id")
    @Expose
    private Object previousId;
    @SerializedName("bitmark_id")
    @Expose
    private String bitmarkId;
    public final static Parcelable.Creator<Tx> CREATOR = new Creator<Tx>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Tx createFromParcel(Parcel in) {
            return new Tx(in);
        }

        public Tx[] newArray(int size) {
            return (new Tx[size]);
        }

    }
            ;
    private final static long serialVersionUID = -6242121929036335586L;

    protected Tx(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.owner = ((String) in.readValue((String.class.getClassLoader())));
        this.assetId = ((String) in.readValue((String.class.getClassLoader())));
        this.head = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.blockNumber = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.blockOffset = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.offset = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.expiresAt = ((Object) in.readValue((Object.class.getClassLoader())));
        this.payId = ((String) in.readValue((String.class.getClassLoader())));
        this.previousId = ((Object) in.readValue((Object.class.getClassLoader())));
        this.bitmarkId = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Tx() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
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

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Object getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Object expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public Object getPreviousId() {
        return previousId;
    }

    public void setPreviousId(Object previousId) {
        this.previousId = previousId;
    }

    public String getBitmarkId() {
        return bitmarkId;
    }

    public void setBitmarkId(String bitmarkId) {
        this.bitmarkId = bitmarkId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(owner);
        dest.writeValue(assetId);
        dest.writeValue(head);
        dest.writeValue(status);
        dest.writeValue(blockNumber);
        dest.writeValue(blockOffset);
        dest.writeValue(offset);
        dest.writeValue(expiresAt);
        dest.writeValue(payId);
        dest.writeValue(previousId);
        dest.writeValue(bitmarkId);
    }

    public int describeContents() {
        return 0;
    }

}