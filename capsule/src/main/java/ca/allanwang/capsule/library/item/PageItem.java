package ca.allanwang.capsule.library.item;

import android.support.annotation.StringRes;

import ca.allanwang.capsule.library.fragments.CapsulePageFragment;
import ca.allanwang.capsule.library.interfaces.CPage;

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
