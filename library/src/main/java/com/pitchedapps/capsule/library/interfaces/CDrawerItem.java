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
    <T extends Fragment & CFragmentCore> T getFragment();

    @StringRes
    int getTitleId();

    IIcon getIcon();

    boolean isPrimary();
}
