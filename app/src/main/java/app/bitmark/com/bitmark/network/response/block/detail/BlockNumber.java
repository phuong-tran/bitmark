package app.bitmark.com.bitmark.network.response.block.detail;

import android.support.annotation.NonNull;

public class BlockNumber implements Comparable<Integer> {
    private Integer blockNumber;
    public BlockNumber(Integer blockNumber) {
        this.blockNumber = blockNumber;
    }

    public Integer getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(Integer blockNumber) {
        this.blockNumber = blockNumber;
    }

    @Override
    public int hashCode() {
        return 31 * blockNumber.hashCode();
    }

    @Override
    public int compareTo(@NonNull Integer o) {
        return 0;
    }
}
