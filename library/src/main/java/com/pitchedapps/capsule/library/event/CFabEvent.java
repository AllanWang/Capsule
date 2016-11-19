package com.pitchedapps.capsule.library.event;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.mikepenz.iconics.typeface.IIcon;
import com.pitchedapps.capsule.library.utils.ViewUtils;

/**
 * Created by Allan Wang on 2016-11-14.
 */

public class CFabEvent {

    private int drawable = -1;
    private IIcon mIcon;
    private boolean show = true;
    private View.OnClickListener mListener;

    //You'll typically use this to hide the fab
    public CFabEvent() {
        show = false;
    }

    public CFabEvent(boolean show) {
        this.show = show;
    }

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

    public void load(@NonNull FloatingActionButton fab) {
        //change fab visibility
        if (show) {
            fab.show();
            if (mListener != null) fab.setOnClickListener(mListener);
        } else {
            fab.hide();
        }
        //change icon if applicable
        if (mIcon != null) {
            fab.setImageDrawable(ViewUtils.iconDrawable(fab.getContext(), mIcon));
        } else if (drawable != -1) {
            fab.setImageResource(drawable);
        }
    }
}
