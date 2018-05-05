package app.bitmark.com.bitmark;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import app.bitmark.com.bitmark.network.response.block.detail.BlockDetail;

public class BlockCache {
    public static Map<Integer, BlockDetail> BlockCache = new TreeMap(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    });

    public static List<BlockDetail> getBlockDetails() {
        return new ArrayList<>(BlockCache.values()) ;
    }
}
