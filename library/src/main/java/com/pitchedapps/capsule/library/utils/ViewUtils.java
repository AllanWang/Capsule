package com.pitchedapps.capsule.library.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.IIcon;

import java.util.Random;

/**
 * Created by Allan Wang on 2016-10-30.
 */

public class ViewUtils {

    public static IconicsDrawable iconDrawable(@NonNull Context context, IIcon icon) {
        return iconDrawable(context, icon, android.R.color.white);
    }

    public static IconicsDrawable iconDrawable(@NonNull Context context, IIcon icon, @ColorRes int color) {
        return new IconicsDrawable(context)
                .icon(icon)
                .colorRes(color)
                .sizeDp(24);
    }

    public static int randomLightColor() {
        Random rnd = new Random();
        float r = rnd.nextInt(255);
        float g = rnd.nextInt(255);
        float b = rnd.nextInt(255);
        r = r * 0.1f + 255 * 0.9f;
        g = g * 0.1f + 255 * 0.9f;
        b = b * 0.1f + 255 * 0.9f;
        return Color.rgb((int) r, (int) g, (int) b);
    }

    public static int randomDarkColor() {
        Random rnd = new Random();
        float r = rnd.nextInt(255);
        float g = rnd.nextInt(255);
        float b = rnd.nextInt(255);
        r *= 0.1f;
        g *= 0.1f;
        b *= 0.1f;
        return Color.rgb((int) r, (int) g, (int) b);
    }
}
