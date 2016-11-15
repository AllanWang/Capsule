package com.pitchedapps.capsule.library.item;

import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.View;

import com.mikepenz.iconics.typeface.IIcon;
import com.pitchedapps.capsule.library.fragments.CapsulePageFragment;
import com.pitchedapps.capsule.library.interfaces.CFragmentCore;
import com.pitchedapps.capsule.library.interfaces.CPage;

/**
 * Created by Allan Wang on 2016-10-30.
 */

public class PageItem implements CPage {

    private CapsulePageFragment mFragment;
    private int mTitleId;

    public PageItem(CapsulePageFragment fragment, @StringRes int titleId) {
        mFragment = fragment;
        mTitleId = titleId;
    }

    @Override
    public CapsulePageFragment getFragment() {
        return mFragment;
    }

    @Override
    public int getTitleId() {
        return mTitleId;
    }
}
