package com.pitchedapps.capsule.library.item;

import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.View;

import com.mikepenz.iconics.typeface.IIcon;
import com.pitchedapps.capsule.library.interfaces.CFragmentCore;
import com.pitchedapps.capsule.library.interfaces.CPage;

/**
 * Created by Allan Wang on 2016-10-30.
 */

public class PageItem implements CPage {

    private Fragment mFragment;
    private int mTitleId;
    private IIcon mIcon;
    private View.OnClickListener mListener;

    public <T extends Fragment & CFragmentCore> PageItem(T fragment, @StringRes int titleId, IIcon icon, @Nullable View.OnClickListener onFabClick) {
        mFragment = fragment;
        mTitleId = titleId;
        mIcon = icon;
        mListener = onFabClick;
    }

    @Override
    public int getTitle() {
        return mTitleId;
    }

    @Override
    public <T extends Fragment & CFragmentCore> T getFragment() {
        return (T) mFragment;
    }

    @Override
    public boolean hasFab() {
        return mListener != null;
    }

    @Override
    public IIcon getFabIcon() {
        return mIcon;
    }

    @Override
    public void onFabClick(View v) {
        if (hasFab())
            mListener.onClick(v); //Make sure it's valid, even though hasFab should always be checked
    }

    @Override
    public int getTitleId() {
        return mTitleId;
    }
}
