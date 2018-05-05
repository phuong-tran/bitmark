package app.bitmark.com.bitmark.network;


import app.bitmark.com.bitmark.network.response.PageInfo;
import app.bitmark.com.bitmark.network.response.block.detail.BlockDetail;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BitmarkWebService {
    @GET("info")
    Observable<PageInfo> fetchInfoObservable();

    @GET("info")
    Single<PageInfo> fetchInfoSingle();

    @GET("txs")
    Observable<BlockDetail> fetchBlockObservable(
            @Query("asset") String asset,
            @Query("block") String block,
            @Query("at") int at,
            @Query("block_number") int blockNumber,
            @Query("to") String to,
            @Query("limit") int limit
        );

    @GET("txs")
    Single<BlockDetail> fetchBlockSingle(
            @Query("asset") String asset,
            @Query("block") String block,
            @Query("at") int at,
            @Query("block_number") int blockNumber,
            @Query("to") String to,
            @Query("limit") int limit
    );
}
