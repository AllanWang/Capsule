package com.pitchedapps.capsule.library.fragments;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pitchedapps.capsule.library.R;
import com.pitchedapps.capsule.library.adapters.ViewPagerAdapter;
import com.pitchedapps.capsule.library.event.CFabEvent;
import com.pitchedapps.capsule.library.interfaces.CPage;

import java.util.List;

/**
 * Created by Allan Wang on 2016-08-19.
 */
public abstract class ViewPagerFragment extends CapsuleFragment {

    protected abstract List<CPage> setPages();

    //fab shall be updated through inner fragments
    @Nullable
    @Override
    protected CFabEvent updateFab() {
        return null;
    }

    @Override
    @CallSuper
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_viewpager, container, false);
        ViewPager mViewPager = (ViewPager) v.findViewById(R.id.viewpager);
        ViewPagerAdapter mAdapter = new ViewPagerAdapter(getContext(), getChildFragmentManager(), mViewPager, setPages());
        mViewPager.setAdapter(mAdapter);
        ((TabLayout) v.findViewById(R.id.tabs)).setupWithViewPager(mViewPager);
        return v;
    }

    @Override
    public int getTitleId() {
        return 0;
    }


}
