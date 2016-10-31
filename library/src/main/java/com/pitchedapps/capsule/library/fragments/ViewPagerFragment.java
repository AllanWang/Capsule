package com.pitchedapps.capsule.library.fragments;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.pitchedapps.capsule.library.R;
import com.pitchedapps.capsule.library.adapters.ViewPagerAdapter;
import com.pitchedapps.capsule.library.interfaces.CActivityCore;
import com.pitchedapps.capsule.library.interfaces.CPage;
import com.pitchedapps.capsule.library.logging.CLog;
import com.pitchedapps.capsule.library.utils.ContextUtils;
import com.pitchedapps.capsule.library.utils.ViewUtils;

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
        setFabIcon(GoogleMaterial.Icon.gmd_account_balance);
        View v = inflater.inflate(R.layout.fragment_viewpager, container, false);
        ViewPager mViewPager = (ViewPager) v.findViewById(R.id.viewpager);
        mAdapter = new ViewPagerAdapter(getContext(), getChildFragmentManager(), mViewPager, setPages());
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
