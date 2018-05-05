package app.bitmark.com.bitmark;

import android.app.Activity;
import android.app.Application;

import javax.inject.Inject;

import app.bitmark.com.bitmark.di.AppInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;


public class App extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        AppInjector.init(this);
    }

}
