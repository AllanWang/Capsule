package ca.allanwang.capsule.library.item;

import android.support.annotation.StringRes;

import com.mikepenz.iconics.typeface.IIcon;

import ca.allanwang.capsule.library.interfaces.CDrawerItem;

/**
 * Created by Allan Wang on 2016-10-30.
 */

public abstract class DrawerItem implements CDrawerItem {

    public static final int DIVIDER = -1;
    private int mTitleId;
    private IIcon mIcon;
    private boolean mIsPrimary;

    public DrawerItem(int type) {
        mTitleId = type;
    }

    public DrawerItem(@StringRes int titleId, IIcon icon, boolean isPrimary) {
        mTitleId = titleId;
        mIcon = icon;
        mIsPrimary = isPrimary;
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
