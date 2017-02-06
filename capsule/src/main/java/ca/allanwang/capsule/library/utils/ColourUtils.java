package ca.allanwang.capsule.library.utils;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;

import java.util.Random;

/**
 * Created by Allan Wang on 2016-11-18.
 */

public class ColourUtils {

    public static int getBackgroundColor(Context context) {
        TypedValue a = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.colorBackground, a, true);
        return a.data;
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
