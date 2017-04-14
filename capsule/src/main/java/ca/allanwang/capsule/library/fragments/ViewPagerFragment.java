package ca.allanwang.capsule.library.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ca.allanwang.capsule.library.R;
import ca.allanwang.capsule.library.adapters.ViewPagerAdapter;
import ca.allanwang.capsule.library.event.CFabEvent;
import ca.allanwang.capsule.library.interfaces.CPage;
import ca.allanwang.capsule.library.interfaces.CPageFragment;

/**
 * Created by Allan Wang on 2016-08-19.
 */
public abstract class ViewPagerFragment extends CapsuleFragment implements TabLayout.OnTabSelectedListener {

    protected abstract List<CPage> setPages();
    private ViewPagerAdapter mAdapter;

    //fab shall be updated through inner fragments
    @Nullable
    @Override
    protected CFabEvent updateFab() {
        return null;
    }

    @Override
    @CallSuper
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.capsule_fragment_viewpager, container, false);
        ViewPager viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        List<CPage> pages = setPages();
        mAdapter = setAdapter(getContext(), getChildFragmentManager(), viewPager, pages);
        viewPager.setAdapter(mAdapter);
        TabLayout tabs = (TabLayout) v.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        tabs.addOnTabSelectedListener(this);
        viewPager.setOffscreenPageLimit(getOffscreenPageLimit(pages.size()));
        viewPagerSetup(viewPager, pages.size());
        return v;
    }

    /**
     * Called when a tab enters the selected state.
     *
     * @param tab The tab that was selected
     */
    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    /**
     * Called when a tab exits the selected state.
     *
     * @param tab The tab that was unselected
     */
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    /**
     * Called when a tab that is already selected is chosen again by the user. Some applications
     * may use this action to return to the top level of a category.
     *
     * @param tab The tab that was reselected.
     */
    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        Fragment fragment = mAdapter.getCurrentFragment();
        if (fragment instanceof CPageFragment)
            ((CPageFragment) fragment).onSelected(tab.getPosition(), tab.getPosition());
    }

    protected abstract int getOffscreenPageLimit(int pageCount);

    /**
     * Optional helper method to modify the viewpager after it is created
     *
     * @param viewPager new loaded viewpager
     */
    protected void viewPagerSetup(ViewPager viewPager, int pageCount) {
    }

    /**
     * Method for adapter retrieval; can be overridden
     *
     * @param context         fragment context
     * @param fragmentManager childFragmentManager
     * @param viewPager       the viewpager it will be set to
     * @param pages           all the pages for the tabs
     * @return new adapter extending ViewPagerAdapter
     */
    protected ViewPagerAdapter setAdapter(Context context, FragmentManager fragmentManager, ViewPager viewPager, List<CPage> pages) {
        return new ViewPagerAdapter(context, fragmentManager, viewPager, pages);
    }


}
