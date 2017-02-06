package ca.allanwang.capsule.sample.fragments;

import android.support.annotation.Nullable;

import ca.allanwang.capsule.library.event.CFabEvent;

/**
 * Created by Allan Wang on 2016-08-21.
 */
public class FragmentSampleNoFab extends FragmentSample {

    @Nullable
    @Override
    protected CFabEvent updateFab() {
        return new CFabEvent(false);
    }

}
