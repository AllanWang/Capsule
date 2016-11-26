package com.pitchedapps.capsule.library.utils;

import android.support.annotation.Nullable;

import com.pitchedapps.capsule.library.event.CFabEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Allan Wang on 2016-11-26.
 */

public class EventUtils {

    public static void post(@Nullable Object event) {
        if (event == null) return;
        EventBus.getDefault().post(event);
    }

    public static void hideFab() {
        post(new CFabEvent(false));
    }
}
