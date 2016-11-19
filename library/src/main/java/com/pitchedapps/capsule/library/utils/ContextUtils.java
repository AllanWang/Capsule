package com.pitchedapps.capsule.library.utils;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;

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

    public static String getText(Context context, String s, @StringRes int si) {
        if (si != -1) return ResUtils.s(context, si);
        return s;
    }

    public static int getColor(Context context, @ColorRes int colorRes, @ColorInt int colorInt) {
        if (colorRes != -1) return ContextCompat.getColor(context, colorRes);
        return colorInt;
    }
}
