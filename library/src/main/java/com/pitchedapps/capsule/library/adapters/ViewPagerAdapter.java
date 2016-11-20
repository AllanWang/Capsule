package com.pitchedapps.capsule.library.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.pitchedapps.capsule.library.interfaces.CPage;
import com.pitchedapps.capsule.library.logging.CLog;

import java.util.List;

/**
 * Created by Allan Wang on 2016-10-30.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

    private Context mContext;
    private List<CPage> mPages;
    private int mPosition;

    public ViewPagerAdapter(Context context, FragmentManager fm, ViewPager viewPager, @NonNull List<CPage> pages) {
        super(fm);
        mContext = context;
        mPages = pages;
        if (pages.size() <= 1) {
            CLog.d("ViewPager list is less than 2. No need for ViewPager");
        }
        viewPager.setAdapter(this);
        viewPager.addOnPageChangeListener(this);
        onPageSelected(0);
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        return mPages.get(position).getFragment();
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return mPages.size();
    }

    @Override
    public String getPageTitle(int position) {
        if (mPages.get(position).getTitleId() <= 0) return null;
        return (mContext.getString(mPages.get(position).getTitleId()));
    }

    public int getPosition() {
        return mPosition;
    }

    public CPage getCurrentPage() {
        return mPages.get(mPosition);
    }

    /**
     * This method will be invoked when the current page is scrolled, either as part
     * of a programmatically initiated smooth scroll or a user initiated touch scroll.
     *
     * @param position             Position index of the first page currently being displayed.
     *                             Page position+1 will be visible if positionOffset is nonzero.
     * @param positionOffset       Value from [0, 1) indicating the offset from the page at position.
     * @param positionOffsetPixels Value in pixels indicating the offset from position.
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * This method will be invoked when a new page becomes selected. Animation is not
     * necessarily complete.
     *
     * @param position Position index of the new selected page.
     */
    @Override
    public void onPageSelected(int position) {
        mPosition = position;
        mPages.get(position).getFragment().onSelected();
    }

    /**
     * Called when the scroll state changes. Useful for discovering when the user
     * begins dragging, when the pager is automatically settling to the current page,
     * or when it is fully stopped/idle.
     *
     * @param state The new scroll state.
     * @see ViewPager#SCROLL_STATE_IDLE
     * @see ViewPager#SCROLL_STATE_DRAGGING
     * @see ViewPager#SCROLL_STATE_SETTLING
     */
    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
