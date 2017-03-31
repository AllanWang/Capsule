package ca.allanwang.capsule.library.item;

import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

import com.mikepenz.iconics.typeface.IIcon;

import ca.allanwang.capsule.library.interfaces.CDrawerItem;

/**
 * Created by Allan Wang on 2016-10-30.
 */

public class DrawerItem implements CDrawerItem {

    public static final int DIVIDER = -1;
    private int mTitleId;
    private IIcon mIcon;
    private boolean mIsPrimary;
    private DrawerFragment mDrawerFragment;

    public DrawerItem(int type) {
        mTitleId = type;
    }

    public interface DrawerFragment {
        @Nullable
        Fragment getFragment();
    }

    public DrawerItem(@StringRes int titleId, IIcon icon, boolean isPrimary, DrawerFragment drawerFragment) {
        mTitleId = titleId;
        mIcon = icon;
        mIsPrimary = isPrimary;
        mDrawerFragment = drawerFragment;
    }

    @Nullable
    @Override
    public Fragment getFragment() {
        return mDrawerFragment == null ? null : mDrawerFragment.getFragment();
    }

    @Override
    public int getTitleId() {
        return mTitleId;
    }

    @Override
    public IIcon getIcon() {
        return mIcon;
    }

    @Override
    public boolean isPrimary() {
        return mIsPrimary;
    }
}
