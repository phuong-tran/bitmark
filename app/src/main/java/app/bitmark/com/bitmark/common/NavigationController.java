package app.bitmark.com.bitmark.common;

import android.support.v4.app.FragmentManager;

import javax.inject.Inject;


import app.bitmark.com.bitmark.R;
import app.bitmark.com.bitmark.ui.activities.MainActivity;
import app.bitmark.com.bitmark.ui.fragments.AboutFragment;
import app.bitmark.com.bitmark.ui.fragments.MainFragment;

public class NavigationController {
    private final int containerId;
    private final FragmentManager fragmentManager;

    @Inject
    public NavigationController(MainActivity mainActivity) {
        this.containerId = R.id.container;
        this.fragmentManager = mainActivity.getSupportFragmentManager();
    }

    public void navigateToHome() {
        MainFragment mainFragment = MainFragment.newInstance();
        fragmentManager.beginTransaction()
                .replace(containerId, mainFragment)
                .commitAllowingStateLoss();
    }

    public void navigateToAbout() {
        AboutFragment aboutFragment = AboutFragment.newInstance();
        fragmentManager.beginTransaction()
                .replace(containerId, aboutFragment)
                .commitAllowingStateLoss();
    }

}