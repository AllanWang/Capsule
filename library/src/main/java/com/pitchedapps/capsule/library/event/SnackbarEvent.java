package com.pitchedapps.capsule.library.event;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.pitchedapps.capsule.library.utils.ResUtils;

/**
 * Created by Allan Wang on 2016-11-14.
 */

public class SnackbarEvent {

    private String text, actionText;
    private int textId = -1, actionTextId = -1;
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

    private String getText(Context context, String s, @StringRes int si) {
        if (s != null) return s;
        if (si == -1) return null;
        return ResUtils.s(context, si);
    }

    public void load(@NonNull View view) {
        //if text is not defined, textId is
        Snackbar snackbar = Snackbar.make(view, getText(view.getContext(), text, textId), duration);
        if (mListener != null)
            snackbar.setAction(getText(view.getContext(), actionText, actionTextId), mListener);
        snackbar.show();
    }
}
