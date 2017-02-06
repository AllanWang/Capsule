package ca.allanwang.capsule.library.utils;

import android.support.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

import ca.allanwang.capsule.library.event.CFabEvent;

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
