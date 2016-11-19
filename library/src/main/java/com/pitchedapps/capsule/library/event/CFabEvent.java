package com.pitchedapps.capsule.library.event;

import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.mikepenz.iconics.typeface.IIcon;
import com.pitchedapps.capsule.library.utils.ContextUtils;
import com.pitchedapps.capsule.library.utils.ViewUtils;

/**
 * Created by Allan Wang on 2016-11-14.
 * Event containing info on fab change
 */

public class CFabEvent {

    private int drawable = -1;
    private IIcon mIcon;
    private int colorInt = -1, colorRes = -1;
    private boolean show = true;
    private View.OnClickListener mListener;

    /**
     * Shows or hides the fab
     * Listeners and icons are unchanged
     *
     * @param show true for show, false for hide
     */
    public CFabEvent(boolean show) {
        this.show = show;
    }

    /**
     * Changes click result of fab; will show the fab
     *
     * @param listener the new click callback
     */
    public CFabEvent(View.OnClickListener listener) {
        mListener = listener;
    }

    public CFabEvent(IIcon icon, View.OnClickListener listener) {
        mIcon = icon;
        mListener = listener;
    }

    public CFabEvent(@DrawableRes int drawable, View.OnClickListener listener) {
        this.drawable = drawable;
        mListener = listener;
    }

    /**
     * Optional color change
     *
     * @param color background color int
     * @return
     */
    public CFabEvent setBackgroundColor(@ColorInt int color) {
        colorInt = color;
        return this;
    }

    /**
     * Optional color change
     *
     * @param color background color res
     * @return
     */
    public CFabEvent setBackgroundColorRes(@ColorRes int color) {
        colorRes = color;
        return this;
    }

    /**
     * Modifies the passed fab based on the event
     *
     * @param fab to be modified
     */
    public void load(@NonNull FloatingActionButton fab) {
        //change fab visibility
        if (!show) {
            fab.hide();
            return;
        }
        fab.show();
        if (mListener != null) fab.setOnClickListener(mListener);
        //change icon if applicable
        if (mIcon != null) {
            fab.setImageDrawable(ViewUtils.iconDrawable(fab.getContext(), mIcon));
        } else if (drawable != -1) {
            fab.setImageResource(drawable);
        }
        int bg = ContextUtils.getColor(fab.getContext(), colorRes, colorInt);
        if (bg != -1) fab.setBackgroundColor(bg);
    }
}
