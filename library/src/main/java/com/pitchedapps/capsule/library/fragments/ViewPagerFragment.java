package com.pitchedapps.capsule.library.fragments;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pitchedapps.capsule.library.R;
import com.pitchedapps.capsule.library.adapters.ViewPagerAdapter;
import com.pitchedapps.capsule.library.interfaces.CPage;

import java.util.List;

/**
 * Created by Allan Wang on 2016-08-19.
 */
public abstract class ViewPagerFragment extends BaseFragment {

    private ViewPagerAdapter mAdapter;

    protected abstract List<CPage> setPages();

    @Override
    @CallSuper
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_viewpager, container, false);
        mAdapter = new ViewPagerAdapter(getContext(), getChildFragmentManager(), setPages());
        ViewPager mViewPager = (ViewPager) v.findViewById(R.id.viewpager);
        mViewPager.setAdapter(mAdapter);
        ((TabLayout) v.findViewById(R.id.tabs)).setupWithViewPager(mViewPager);
        return v;
    }

    @Override
    public void onFabClick(View v) {
        if (mAdapter.getCurrentPage().hasFab()) {
            mAdapter.getCurrentPage().onFabClick(v);
        }
    }

    @Override
    public int getTitleId() {
        return 0;
    }

}
