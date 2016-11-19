package com.pitchedapps.capsule.library.event;

import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.pitchedapps.capsule.library.utils.ContextUtils;

/**
 * Created by Allan Wang on 2016-11-14.
 */

public class SnackbarEvent {

    private String text, actionText;
    private int textId = -1, actionTextId = -1, colorInt = -1, colorRes = -1;
    private int duration = Snackbar.LENGTH_LONG;
    private View.OnClickListener mListener;

    public SnackbarEvent(String s) {
        text = s;
    }

    public SnackbarEvent(@StringRes int si) {
        textId = si;
    }

    public SnackbarEvent setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public SnackbarEvent setAction(String text, View.OnClickListener listener) {
        actionText = text;
        mListener = listener;
        return this;
    }

    public SnackbarEvent setAction(@StringRes int textId, View.OnClickListener listener) {
        actionTextId = textId;
        mListener = listener;
        return this;
    }

    public SnackbarEvent setColorRes(@ColorRes int color) {
        colorRes = color;
        return this;
    }

    public SnackbarEvent setColor(@ColorInt int color) {
        colorInt = color;
        return this;
    }

    public void load(@NonNull View view) {
        Snackbar snackbar = Snackbar.make(view, ContextUtils.getText(view.getContext(), text, textId), duration);
        if (mListener != null)
            snackbar.setAction(ContextUtils.getText(view.getContext(), actionText, actionTextId), mListener);
        int bgColor = ContextUtils.getColor(view.getContext(), colorRes, colorInt);
        if (bgColor != -1) snackbar.getView().setBackgroundColor(bgColor);
        snackbar.show();
    }
}
