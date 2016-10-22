package com.pitchedapps.capsule.library.interfaces;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

/**
 * Created by Allan Wang on 2016-10-22.
 */

public interface CDrawerItem {

    @Nullable
    <T extends Fragment & CFragmentCore> T getFragment();

    @StringRes
    int getTitleId();

    Drawable getIcon();

    boolean isPrimary();
}
