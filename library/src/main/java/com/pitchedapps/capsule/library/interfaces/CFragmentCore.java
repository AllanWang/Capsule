package com.pitchedapps.capsule.library.interfaces;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.View;

/**
 * Created by Allan Wang on 2016-10-09.
 * <p>
 * Contains list of methods used by CapsuleActivity
 */

public interface CFragmentCore {

    void onFabClick(View v);

    @StringRes
    int getTitleId();

    @DrawableRes
    int getFabIcon();

    /**
     * Will hide the fab if false; the fab is still in the viewgroup and is used for various other tasks such as the snackbar
     *
     * @return fab existence
     */
    boolean hasFab();

    void showFab();

    void hideFab();

}
