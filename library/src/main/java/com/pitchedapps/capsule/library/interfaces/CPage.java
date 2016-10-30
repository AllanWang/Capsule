package com.pitchedapps.capsule.library.interfaces;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

import com.mikepenz.iconics.typeface.IIcon;

/**
 * Created by Allan Wang on 2016-10-30.
 */

public interface CPage extends CFragmentCore {

    @StringRes
    int getTitle();

    <T extends Fragment & CFragmentCore> T getFragment();

    boolean hasFab();

    IIcon getFabIcon();
}
