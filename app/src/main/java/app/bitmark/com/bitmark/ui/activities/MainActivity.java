package app.bitmark.com.bitmark.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import app.bitmark.com.bitmark.BlockCache;
import app.bitmark.com.bitmark.R;
import app.bitmark.com.bitmark.common.NavigationController;
import app.bitmark.com.bitmark.network.BitmarkWebService;
import app.bitmark.com.bitmark.network.NetworkApiUtil;
import app.bitmark.com.bitmark.network.response.PageInfo;
import app.bitmark.com.bitmark.network.response.block.detail.BlockDetail;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    private static Logger LOG = LoggerFactory.getLogger(MainActivity.class);

    @NonNull
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    @Inject
    NavigationController navigationController;

    @Inject
    NetworkApiUtil networkApiUtil;

    @Inject
    BitmarkWebService bitmarkWebService;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    navigationController.navigateToHome();
                    return true;
                case R.id.navigation_dashboard:
                    navigationController.navigateToAbout();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (savedInstanceState == null) {
            navigationController.navigateToHome();
        }


    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //fetchInfo();
    }

    private void test() {
       /* bitmarkWebService.pageCount().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(response -> {
                    LOG.debug("HELLO {}", response.getHeight());
                }, error -> {
                    LOG.debug("ERROR {}", error.getMessage());
                });*/


       /* bitmarkWebService.pageCountObservable().
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<PageCount>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PageCount pageCount) {
                        LOG.debug("PHUONG onNext {}", pageCount.getHeight());
                    }

                    @Override
                    public void onError(Throwable onError) {
                        LOG.debug("PHUONG onError {}", onError.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LOG.debug("PHUONG onComplete");
                    }
                });*/
    }


    private void test1() {

        networkApiUtil.fetchBlockObservableDetault(7324).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<BlockDetail>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BlockDetail pageCount) {
                        LOG.debug("PHUONG onNext");
                    }

                    @Override
                    public void onError(Throwable onError) {
                        LOG.debug("PHUONG onError {}", onError.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LOG.debug("PHUONG onComplete");
                    }
                });
    }

    public void test4() {
        Iterable<Observable<BlockDetail>> iterable = networkApiUtil.buildBlockObservable(7325, 7324);

        Observable<BlockDetail> observable = Observable.merge(iterable);

        observable.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<BlockDetail>() {
            private int count = 0;
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BlockDetail blockDetail) {
                count ++;
                if (blockDetail.getBlocks() != null && blockDetail.getBlocks().size() > 0 && blockDetail.getBlocks().get(0).getNumber() != null) {
                    BlockCache.BlockCache.put(blockDetail.getBlocks().get(0).getNumber(), blockDetail);
                }
                LOG.debug("PHUONG {}", blockDetail.getTxs().get(0).getBlockNumber());
            }

            @Override
            public void onError(Throwable e) {
                LOG.debug("PHUONG onError {}", e.getMessage());
            }

            @Override
            public void onComplete() {
                LOG.debug("PHUONG onComplete {}", count);
            }
        });
    }

    private void fetchInfo() {
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
                        LOG.debug("PHUONG onError {}", e.getMessage());
                    }


                    @Override
                    public void onNext(PageInfo pageInfo) {
                        test4();
                        LOG.debug("PHUONG onNext {}", pageInfo.getHeight());
                    }
                }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }
}
