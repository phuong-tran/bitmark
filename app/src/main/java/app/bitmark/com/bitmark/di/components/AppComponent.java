package app.bitmark.com.bitmark.di.components;

import android.app.Application;

import javax.inject.Singleton;

import app.bitmark.com.bitmark.App;
import app.bitmark.com.bitmark.di.modules.AppModule;
import app.bitmark.com.bitmark.di.modules.MainActivityBuilderModule;
import app.bitmark.com.bitmark.di.modules.NetworkModule;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AppModule.class,
        MainActivityBuilderModule.class,
        NetworkModule.class
})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(App app);
}