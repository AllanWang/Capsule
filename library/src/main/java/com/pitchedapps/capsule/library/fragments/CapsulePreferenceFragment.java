package com.pitchedapps.capsule.library.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pitchedapps.capsule.library.R;
import com.pitchedapps.capsule.library.activities.CapsuleActivity;
import com.pitchedapps.capsule.library.views.RippleView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Allan Wang on 2016-11-17.
 */

public abstract class CapsulePreferenceFragment extends PreferenceFragmentCompat {

    private RippleView mRippleView;

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        ContextThemeWrapper themeContext = new ContextThemeWrapper(getContext(), R.style.CapsuleRipplePreferenceTheme);
//        LayoutInflater themedInflater = inflater.cloneInContext(themeContext);
//        return super.onCreateView(themedInflater, container, savedInstanceState);
//    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRippleView = (RippleView) view.findViewById(R.id.ripple_bg);
    }

    public RippleView bg() {
        return mRippleView;
    }

    protected String s(@StringRes int id) {
        return getString(id);
    }

     /*
     * The following methods require a CapsuleActivity context
     */

    protected void postEvent(Object event) {
        if (event == null) return;
        EventBus.getDefault().post(event);
    }

    protected CapsuleActivity capsuleActivity() {
        if (!(getActivity() instanceof CapsuleActivity)) {
            throw new RuntimeException(s(R.string.capsule_activity_context_error));
        }
        return ((CapsuleActivity) getActivity());
    }
}
