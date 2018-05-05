package app.bitmark.com.bitmark.di.modules;


import app.bitmark.com.bitmark.ui.fragments.AboutFragment;
import app.bitmark.com.bitmark.ui.fragments.MainFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
@SuppressWarnings("unused")
@Module
public abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract MainFragment contributeMainFragment();

    @ContributesAndroidInjector
    abstract AboutFragment contributeAboutFragment();
}
