package com.pitchedapps.capsule.library.utils;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.IIcon;

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

    public static void setOnClickPositionListener(View v, @NonNull final OnClickPositionListener listener) {
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    listener.onClick(view, motionEvent.getX(), motionEvent.getY());
                    return true;
                }
                return false;
            }
        });
    }

    public interface OnClickPositionListener {
        void onClick(View view, float x, float y);
    }

    public static int dpToPx(double dp) {
        // resources instead of context !!
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5);
    }

    public static int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
}
