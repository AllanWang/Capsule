package ca.allanwang.capsule.library.item;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

import ca.allanwang.capsule.library.interfaces.CPage;
import ca.allanwang.capsule.library.interfaces.CPageFragment;

/**
 * Created by Allan Wang on 2016-10-30.
 */

public class PageItem<T extends Fragment & CPageFragment> implements CPage<T> {

    private T mFragment;
    private int mTitleId;

    public PageItem(T fragment, @StringRes int titleId) {
        mFragment = fragment;
        mTitleId = titleId;
    }

    @Override
    public T getFragment() {
        return mFragment;
    }

    @Override
    public int getTitleId() {
        return mTitleId;
    }
}
