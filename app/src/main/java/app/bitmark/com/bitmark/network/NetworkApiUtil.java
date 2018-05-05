package app.bitmark.com.bitmark.network;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.bitmark.com.bitmark.network.response.block.detail.BlockDetail;
import io.reactivex.Observable;
import io.reactivex.Single;

@Singleton
public class NetworkApiUtil {
    public static String ENABLE = "true";
    public static String DISABLE = "false";
    public static int DEFAULT_LIMIT = 100;
    public static int PAGE_SIZE = 20;
    public static String LATER = "later";
    public static String EARLIER = "earlier";
    private int pageCount = 0;

    @Inject
    BitmarkWebService bitmarkWebService;

    @Inject
    public NetworkApiUtil() {
    }

    public Observable<BlockDetail> fetchBlockObservableDetault(int blockNumber) {
        return bitmarkWebService.fetchBlockObservable(ENABLE, ENABLE, 0, blockNumber, LATER, DEFAULT_LIMIT);
    }

    public Single<BlockDetail> fetchBlockObservableSingle(BitmarkWebService bitmarkWebService, int blockNumber) {
        return bitmarkWebService.fetchBlockSingle(ENABLE, ENABLE, 0, blockNumber, LATER, DEFAULT_LIMIT);
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<Integer> buildListBock(int currentBlock) {
        List<Integer> blockList = new ArrayList();
        for(int i = 1; i <= PAGE_SIZE; i ++) {
            int value = currentBlock - i;
            if (value > 0) {
                blockList.add(value);
            }
        }
        return blockList;
    }

    public List<Observable<BlockDetail>> buildBlockObservable(int from, int downto) {
        List<Observable<BlockDetail>> blocks = new ArrayList<>();
        int total = from - downto -1;
        for(int i = 0; i <= total; i++) {
            int val = from - i;
            if (val > 0) {
                blocks.add(fetchBlockObservableDetault(val));
            }
        }
        return blocks;
    }
}
