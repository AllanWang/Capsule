package com.pitchedapps.capsule.library.activities;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.pitchedapps.capsule.library.event.CFabEvent;
import com.pitchedapps.capsule.library.event.SnackbarEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Allan Wang on 2016-11-14.
 * Activity with all the EventBus hooks
 */

public abstract class EventActivity extends UtilsActivity {

    @Subscribe
    public void onFabEvent(@NonNull CFabEvent event) {
        event.load(this, cFab);
    }

    @Subscribe
    public void onSnackbarEvent(@NonNull SnackbarEvent event) {
        View view = cFab; //hooking to fab helps move it with the snackbar
        if (view == null) {
            view = ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        }
        event.load(view);
    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
