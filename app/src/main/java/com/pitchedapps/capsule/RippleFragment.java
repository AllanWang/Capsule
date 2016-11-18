package com.pitchedapps.capsule;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.preference.PreferenceScreen;
import android.view.View;

import com.pitchedapps.capsule.library.event.CFabEvent;
import com.pitchedapps.capsule.library.fragments.CapsulePreferenceFragment;
import com.pitchedapps.capsule.library.logging.CLog;
import com.pitchedapps.capsule.library.utils.ViewUtils;
import com.pitchedapps.capsule.library.views.RippleView;

/**
 * Created by Allan Wang on 2016-11-17.
 */

public class RippleFragment extends CapsulePreferenceFragment {

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
        postEvent(new CFabEvent(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bg().ripple(ViewUtils.randomLightColor(), RippleView.MIDDLE, RippleView.MIDDLE);
            }
        }));
    }
}
