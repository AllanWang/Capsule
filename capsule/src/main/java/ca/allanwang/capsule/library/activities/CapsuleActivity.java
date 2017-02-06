package ca.allanwang.capsule.library.activities;

import android.os.Bundle;
import android.support.annotation.AnimRes;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;

import ca.allanwang.capsule.library.interfaces.CFragmentCore;

/**
 * Created by Allan Wang on 2016-08-19.
 * <p>
 * The activity that holds everything, with a few extra helper methods
 */
public abstract class CapsuleActivity extends EventActivity {

    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        capsuleOnCreate(savedInstanceState);
    }

    /**
     * Changes fragment in base view to new fragment
     *
     * @param fragment new fragment to switch to
     */
    protected void switchFragment(Fragment fragment) {
        String tag = null;
        if (fragment instanceof CFragmentCore && ((CFragmentCore) fragment).getTitleId() > 0) {
            tag = s(((CFragmentCore) fragment).getTitleId());
        }
        getSupportFragmentManager()
                .beginTransaction().replace(getFragmentId(), fragment, tag).commit();
        setTitle(tag);
    }

    /**
     * Switch fragment.
     *
     * @param fragment fragment to switch to
     * @param enter    animation
     * @param exit     animation
     * @see #switchFragment(Fragment) #switchFragment(Fragment)
     */
    protected void switchFragment(Fragment fragment, @AnimRes int enter,
                                  @AnimRes int exit) {
        String tag = null;
        if (fragment instanceof CFragmentCore && ((CFragmentCore) fragment).getTitleId() > 0) {
            tag = s(((CFragmentCore) fragment).getTitleId());
        }
        getSupportFragmentManager()
                .beginTransaction().setCustomAnimations(enter, exit)
                .replace(getFragmentId(), fragment, tag).commit();
        setTitle(tag);
    }

    /**
     * Switch fragment.
     *
     * @param fragment fragment to switch to
     * @param enter    enter animation
     * @param exit     exit animation
     * @param popEnter pop enter animation
     * @param popExit  pop exist animation
     * @see #switchFragment(Fragment) #switchFragment(Fragment)
     */
    protected void switchFragment(Fragment fragment, @AnimRes int enter,
                                  @AnimRes int exit, @AnimRes int popEnter, @AnimRes int popExit) {

        String tag = null;
        if (fragment instanceof CFragmentCore && ((CFragmentCore) fragment).getTitleId() > 0) {
            tag = s(((CFragmentCore) fragment).getTitleId());
        }
        getSupportFragmentManager()
                .beginTransaction().setCustomAnimations(enter, exit, popEnter, popExit)
                .replace(getFragmentId(), fragment, tag).commit();
        setTitle(tag);
    }

}
