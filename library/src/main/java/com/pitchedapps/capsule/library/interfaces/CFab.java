package com.pitchedapps.capsule.library.interfaces;

import android.support.annotation.DrawableRes;

import com.mikepenz.iconics.typeface.IIcon;

/**
 * Created by Allan Wang on 2016-10-30.
 */

public interface CFab {

    IIcon getFabIcon();

    /**
     * Will hide the fab if false; the fab is still in the viewgroup and is used for various other tasks such as the snackbar
     *
     * @return fab existence
     */
    boolean hasFab();

}
