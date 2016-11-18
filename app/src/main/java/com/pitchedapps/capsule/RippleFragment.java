package com.pitchedapps.capsule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;

import com.pitchedapps.capsule.library.event.CFabEvent;
import com.pitchedapps.capsule.library.fragments.CapsulePreferenceFragment;
import com.pitchedapps.capsule.library.logging.CLog;
import com.pitchedapps.capsule.library.utils.ColourUtils;
import com.pitchedapps.capsule.library.utils.ViewUtils;
import com.pitchedapps.capsule.library.views.RippleCanvas;

/**
 * Created by Allan Wang on 2016-11-17.
 */

public class RippleFragment extends CapsulePreferenceFragment {
    int themeStyle = R.style.AppTheme;

    /**
     * Called during {@link #onCreate(Bundle)} to supply the preferences for this fragment.
     * Subclasses are expected to call {@link #setPreferenceScreen(PreferenceScreen)} either
     * directly or via helper methods such as {@link #addPreferencesFromResource(int)}.
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     * @param rootKey            If non-null, this preference fragment should be rooted at the
     *                           {@link PreferenceScreen} with this key.
     */
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.pref_test, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bg().set(ColourUtils.getBackgroundColor(getContext()));
        postEvent(new CFabEvent(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themeStyle = ((MainActivity) getContext()).switchTheme();
                reload();
                bg().ripple(ColourUtils.getBackgroundColor(getContext()), v.getX(), v.getY());
            }
        }));
    }

    public void reload() {
        ContextThemeWrapper newContext = new ContextThemeWrapper(getContext(), themeStyle); //TODO figure out context theme wrappers
        final PreferenceScreen xmlRoot = getPreferenceManager().inflateFromResource(newContext,
                R.xml.pref_test, null);
//        getPreferenceScreen().removeAll();
        setPreferenceScreen(xmlRoot);
    }

    @Override
    protected void onBindPreferences() {
        CLog.e("Bind");
    }
}
