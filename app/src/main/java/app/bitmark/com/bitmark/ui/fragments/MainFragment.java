package app.bitmark.com.bitmark.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.inject.Inject;

import app.bitmark.com.bitmark.BlockCache;
import app.bitmark.com.bitmark.R;
import app.bitmark.com.bitmark.Utils;
import app.bitmark.com.bitmark.di.Injectable;
import app.bitmark.com.bitmark.network.BitmarkWebService;
import app.bitmark.com.bitmark.network.NetworkApiUtil;
import app.bitmark.com.bitmark.network.response.PageInfo;
import app.bitmark.com.bitmark.network.response.block.detail.BlockDetail;
import app.bitmark.com.bitmark.ui.EndlessRecyclerOnScrollListener;
import app.bitmark.com.bitmark.ui.adapters.BlockCardViewAdapter;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainFragment extends Fragment implements Injectable {

    private static Logger LOG = LoggerFactory.getLogger(MainFragment.class);

    @NonNull
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Inject
    NetworkApiUtil networkApiUtil;

    @Inject
    BitmarkWebService bitmarkWebService;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefresh;

    private BlockCardViewAdapter adapter;

    private LinearLayoutManager linearLayoutManager;
    private EndlessRecyclerOnScrollListener mScrollListener = null;

    public MainFragment() {

    }

    private AtomicBoolean isLoading = new AtomicBoolean(false);

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.progress_bar);
        swipeRefresh = view.findViewById(R.id.swipeRefresh);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new BlockCardViewAdapter(getContext());
        recyclerView.setAdapter(adapter);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isLoading.get()) {
                    swipeRefresh.setRefreshing(false);
                    return;
                }
                adapter.getBlockDetails().clear();
                BlockCache.BlockCache.clear();
                networkApiUtil.setPageCount(0);
                adapter.notifyDataSetChanged();
                fetchInfo(false);
            }
        });

        mScrollListener = new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                if (!isLoading.get()) {
                    LOG.debug("LOAD MORE");
                    mScrollListener.setLoading(true);
                    fetchInfo(true);
                }
            }
        };
        recyclerView.addOnScrollListener(mScrollListener);

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isLoading.set(false);
        mCompositeDisposable.clear();
    }

    private void fetchInfo(boolean isLoadmore) {
        isLoading.set(true);
        showProgressBar();
        mCompositeDisposable.add(bitmarkWebService.fetchInfoObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<PageInfo>() {
                    @Override
                    public void onComplete() {
                        LOG.debug("PHUONG onComplete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        isLoading.set(false);
                        hideProgressBar();
                        mScrollListener.setLoading(false);
                        swipeRefresh.setRefreshing(false);
                        Utils.showNetworkError(getContext());
                    }

                    private void pageCountCacheLessThanPageCount(final int pageCount, final int pageCountCache) {
                        List<Observable<BlockDetail>> list = networkApiUtil.buildBlockObservable(pageCount, pageCountCache);
                        list.addAll(networkApiUtil.buildBlockObservable(pageCountCache, pageCountCache - NetworkApiUtil.PAGE_SIZE));
                        fetchBlocks(list);
                    }

                    private void pageCountCacheEmpty(int pageCount) {
                        List<Observable<BlockDetail>> list = networkApiUtil.buildBlockObservable(pageCount, pageCount - NetworkApiUtil.PAGE_SIZE);
                        fetchBlocks(list);
                    }

                    @Override
                    public void onNext(PageInfo pageInfo) {
                        int pageCountCache = networkApiUtil.getPageCount();
                        int pageCount = pageInfo.getHeight();
                        List<BlockDetail> blockDetails = BlockCache.getBlockDetails();

                        int lastBockNumber = 0;
                        if (blockDetails.size() > 0) {
                            BlockDetail lastItem  = blockDetails.get(blockDetails.size() - 1);
                            lastBockNumber = lastItem.getBlockNumber();
                        }
                        if (isLoadmore) {
                            if (pageCountCache == 0) {
                                pageCountCacheEmpty(pageCount);
                            } else  if (pageCountCache < pageCount){
                                pageCountCacheLessThanPageCount(pageCount, pageCountCache);
                            } else if (pageCountCache == pageCount &&
                                    BlockCache.getBlockDetails().get(0) != null &&
                                    lastBockNumber > 0){
                                List<Observable<BlockDetail>> list = networkApiUtil.buildBlockObservable(lastBockNumber, lastBockNumber - NetworkApiUtil.PAGE_SIZE);
                                fetchBlocks(list);
                            } else {
                                isLoading.set(false);
                                mScrollListener.setLoading(false);
                                hideProgressBar();
                            }
                        } else {
                            if (pageCountCache == 0) {
                                pageCountCacheEmpty(pageCount);
                            } else if (pageCountCache < pageCount) {
                                pageCountCacheLessThanPageCount(pageCount, pageCountCache);
                            }
                        }
                    }
                }));
    }

    private void fetchBlocks(Iterable<Observable<BlockDetail>> iterable) {
        mCompositeDisposable.add(Observable.merge(iterable).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<BlockDetail>() {
            private Map<Integer, BlockDetail> temp = new HashMap();

            @Override
            public void onNext(BlockDetail blockDetail) {
                if (blockDetail.getBlocks() != null && blockDetail.getBlocks().size() > 0 && blockDetail.getBlocks().get(0).getNumber() != null) {
                    BlockCache.BlockCache.put(blockDetail.getBlocks().get(0).getNumber(), blockDetail);
                    temp.put(blockDetail.getBlocks().get(0).getNumber(), blockDetail);
                }

                LOG.debug("PHUONG {}", blockDetail.getTxs().get(0).getBlockNumber());
            }

            @Override
            public void onError(Throwable e) {
                isLoading.set(false);
                mScrollListener.setLoading(false);
                swipeRefresh.setRefreshing(false);
                hideProgressBar();
                Utils.showNetworkError(getContext());
            }

            @Override
            public void onComplete() {
                networkApiUtil.setPageCount(BlockCache.getBlockDetails().get(0).getBlockNumber());
                BlockCache.BlockCache.putAll(temp);
                adapter.setBlockDetails(BlockCache.getBlockDetails());
                recyclerView.getAdapter().notifyDataSetChanged();
                isLoading.set(false);
                hideProgressBar();
                swipeRefresh.setRefreshing(false);
                mScrollListener.setLoading(false);
            }
        }));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fetchInfo(false);
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
