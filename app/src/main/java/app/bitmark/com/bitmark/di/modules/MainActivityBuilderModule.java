package app.bitmark.com.bitmark.di.modules;


import app.bitmark.com.bitmark.ui.activities.MainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
@SuppressWarnings("unused")
@Module
public abstract class MainActivityBuilderModule {

    @ContributesAndroidInjector(modules = {FragmentBuilderModule.class})
    abstract MainActivity contributeMainActivity();
}
