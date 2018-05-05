package app.bitmark.com.bitmark.network.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PageInfo {
    @SerializedName("height")
    @Expose
    private Integer height;

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

}