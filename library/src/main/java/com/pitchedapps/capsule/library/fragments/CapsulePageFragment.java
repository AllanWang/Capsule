package com.pitchedapps.capsule.library.fragments;

import com.pitchedapps.capsule.library.event.CFabEvent;
import com.pitchedapps.capsule.library.interfaces.CPageFragment;

/**
 * Created by Allan Wang on 2016-08-19.
 */
public abstract class CapsulePageFragment extends CapsuleFragment implements CPageFragment {

    //block fab event on attach; viewpagers attach all at once
    @Override
    protected CFabEvent updateFabShell() {
        return null;
    }

    @Override
    public void onSelected() {
        postEvent(updateFab());
    }

}
