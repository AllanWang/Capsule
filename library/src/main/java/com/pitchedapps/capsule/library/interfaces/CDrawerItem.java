package com.pitchedapps.capsule.library.interfaces;

import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

import com.mikepenz.iconics.typeface.IIcon;

/**
 * Created by Allan Wang on 2016-10-22.
 */

public interface CDrawerItem {

    @Nullable
    Fragment getFragment();

    /**
     * Should match the fragment's getTitleId. Please also make this unique if you plan on switchin drawer items by id
     * @return valid StringRes id for the title
     */
    @StringRes
    int getTitleId();

    IIcon getIcon();

    boolean isPrimary();
}
