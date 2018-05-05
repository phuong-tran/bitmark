package app.bitmark.com.bitmark.network.response.block.detail;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class BlockDetail implements Serializable, Parcelable {
    @SerializedName("txs")
    @Expose
    private List<Tx> txs = null;
    @SerializedName("assets")
    @Expose
    private List<Asset> assets = null;
    @SerializedName("blocks")
    @Expose
    private List<Block> blocks = null;
    public final static Parcelable.Creator<BlockDetail> CREATOR = new Creator<BlockDetail>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BlockDetail createFromParcel(Parcel in) {
            return new BlockDetail(in);
        }

        public BlockDetail[] newArray(int size) {
            return (new BlockDetail[size]);
        }

    }
            ;
    private final static long serialVersionUID = -7456571505830519615L;

    protected BlockDetail(Parcel in) {
        in.readList(this.txs, (Tx.class.getClassLoader()));
        in.readList(this.assets, (Asset.class.getClassLoader()));
        in.readList(this.blocks, (Block.class.getClassLoader()));
    }

    public BlockDetail() {
    }

    public List<Tx> getTxs() {
        return txs;
    }

    public void setTxs(List<Tx> txs) {
        this.txs = txs;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(txs);
        dest.writeList(assets);
        dest.writeList(blocks);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public boolean equals(Object rhs) {
        return EqualsBuilder.reflectionEquals(this, rhs, false);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, false);
    }

    public Integer getBlockNumber() {
        return (blocks != null && blocks.size() > 0 && blocks.get(0).getNumber() != null) ? blocks.get(0).getNumber() : 0;
    }

    public String getDescription() {
        if (txs != null && txs.size() >0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Tx tx : txs) {
                stringBuilder.
                        append("Description:\n").
                        append("ID:").
                        append(tx.getId()).
                        append("\n").
                        append("Owner:").
                        append(tx.getOwner()).
                        append("\n").
                        append("BitmarkId:").
                        append(tx.getBitmarkId()).
                        append("\n");
            }
            return stringBuilder.toString();
        }
        return "";
    }

}