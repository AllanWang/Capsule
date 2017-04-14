package ca.allanwang.capsule.library.item;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

import ca.allanwang.capsule.library.interfaces.CPage;

/**
 * Created by Allan Wang on 2016-10-30.
 */

public class PageItem implements CPage {

    private Fragment mFragment;
    private int mTitleId;

    public PageItem(Fragment fragment, @StringRes int titleId) {
        mFragment = fragment;
        mTitleId = titleId;
    }

    @Override
    public Fragment getFragment() {
        return mFragment;
    }

    @Override
    public int getTitleId() {
        return mTitleId;
    }
}
