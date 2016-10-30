package com.pitchedapps.capsule.library.utils;

import android.content.Context;

import com.pitchedapps.capsule.library.R;
import com.pitchedapps.capsule.library.interfaces.CActivityCore;

/**
 * Created by Allan Wang on 2016-10-30.
 */

public class ContextUtils {

    public static CActivityCore capsuleActivity(Context context) {
        if (!(context instanceof CActivityCore)) {
            throw new RuntimeException(context.getString(R.string.capsule_activity_context_error));
        }
        return ((CActivityCore) context);
    }
}
