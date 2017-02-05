package com.pitchedapps.capsule.library.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;

/**
 * Created by Allan Wang on 2016-11-14.
 */
public class ResUtils {

    public static String s(Context context, @StringRes int id) {
        return context.getString(id);
    }

    public static Drawable d(Context context, @DrawableRes int id) {
        return ContextCompat.getDrawable(context, id);
    }
}
