package ca.allanwang.capsule.library.activities;

import android.support.annotation.AnimRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import ca.allanwang.capsule.library.interfaces.CFragmentCore;

/**
 * Created by Allan Wang on 2017-03-30.
 *
 * CapsuleActivity subset that manages fragments
 */

public abstract class FragmentManagerActivity extends EventActivity {

    public interface FragmentTransactionCallback {
        void onPreTransaction(FragmentTransaction transaction);
    }

    public void baseAddFragment(Fragment fragment, @Nullable FragmentTransactionCallback callback) {
        String tag = null;
        if (fragment instanceof CFragmentCore && ((CFragmentCore) fragment).getTitleId() > 0)
            tag = s(((CFragmentCore) fragment).getTitleId());
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction().replace(getFragmentId(), fragment).addToBackStack(null);
        if (callback != null) callback.onPreTransaction(transaction);
        transaction.commit();
        setTitle(tag);
    }

    /**
     * Adds fragment in base view on top of new fragment
     *
     * @param fragment new fragment to switch to
     */
    public void addFragment(Fragment fragment) {
        baseAddFragment(fragment, null);
    }

    /**
     * Add fragment.
     *
     * @param fragment fragment to add on
     * @param enter    animation
     * @param exit     animation
     * @see #addFragment(Fragment) #addFragment(Fragment)
     */
    public void addFragment(Fragment fragment, @AnimRes final int enter, @AnimRes final int exit) {
        baseAddFragment(fragment, transaction -> transaction.setCustomAnimations(enter, exit));
    }

    /**
     * Add fragment.
     *
     * @param fragment fragment to add on
     * @param enter    capsule_enter animation
     * @param exit     capsule_exit animation
     * @param popEnter pop capsule_enter animation
     * @param popExit  pop exist animation
     * @see #addFragment(Fragment) #addFragment(Fragment)
     */
    public void addFragment(Fragment fragment, @AnimRes final int enter, @AnimRes final int exit, @AnimRes final int popEnter, @AnimRes final int popExit) {
        baseAddFragment(fragment, transaction -> transaction.setCustomAnimations(enter, exit, popEnter, popExit));
    }

    public void baseSwitchFragment(Fragment fragment, @Nullable FragmentTransactionCallback callback) {
        String tag = null;
        if (fragment instanceof CFragmentCore && ((CFragmentCore) fragment).getTitleId() > 0)
            tag = s(((CFragmentCore) fragment).getTitleId());
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction().replace(getFragmentId(), fragment, tag);
        if (callback != null) callback.onPreTransaction(transaction);
        transaction.commit();
        setTitle(tag);
    }

    /**
     * Changes fragment in base view to new fragment
     *
     * @param fragment new fragment to switch to
     */
    public void switchFragment(Fragment fragment) {
        baseSwitchFragment(fragment, null);
    }

    /**
     * Switch fragment.
     *
     * @param fragment fragment to switch to
     * @param enter    animation
     * @param exit     animation
     * @see #switchFragment(Fragment) #switchFragment(Fragment)
     */
    public void switchFragment(Fragment fragment, @AnimRes final int enter, @AnimRes final int exit) {
        baseSwitchFragment(fragment, transaction -> transaction.setCustomAnimations(enter, exit));
    }

    /**
     * Switch fragment.
     *
     * @param fragment fragment to switch to
     * @param enter    capsule_enter animation
     * @param exit     capsule_exit animation
     * @param popEnter pop capsule_enter animation
     * @param popExit  pop exist animation
     * @see #switchFragment(Fragment) #switchFragment(Fragment)
     */
    public void switchFragment(Fragment fragment, @AnimRes final int enter, @AnimRes final int exit, @AnimRes final int popEnter, @AnimRes final int popExit) {
        baseSwitchFragment(fragment, transaction -> transaction.setCustomAnimations(enter, exit, popEnter, popExit));
    }
}
