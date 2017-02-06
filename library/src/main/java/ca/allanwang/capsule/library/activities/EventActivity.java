package ca.allanwang.capsule.library.activities;

import android.support.annotation.NonNull;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import ca.allanwang.capsule.library.event.CFabEvent;
import ca.allanwang.capsule.library.event.SnackbarEvent;

/**
 * Created by Allan Wang on 2016-11-14.
 * <p>
 * Activity with all the EventBus hooks
 */

abstract class EventActivity extends UtilsActivity {

    @Subscribe
    public void onFabEvent(@NonNull CFabEvent event) {
        event.load(cFab);
    }

    @Subscribe
    public void onSnackbarEvent(@NonNull SnackbarEvent event) {
        event.load(cFab);
    }

    @Override
    protected void onStart() {
        EventBus.getDefault().register(this); //register first as fragments may post events immediately
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
