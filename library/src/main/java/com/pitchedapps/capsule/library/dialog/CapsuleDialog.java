package com.pitchedapps.capsule.library.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.pitchedapps.capsule.library.logging.CLog;

import timber.log.Timber;

/**
 * Created by Allan Wang on 2016-10-09.
 */

public abstract class CapsuleDialog<T> extends DialogFragment {

    /**
     * Retrieves tag
     *
     * @return tag
     */
    protected abstract String getFragmentTag();

    /**
     * Loads the dialog; the most basic implementation would be
     * to just call showDialog(); no param? Try BasicCapsuleDialog
     */
    protected abstract void initialize(final FragmentActivity activity, final T param);

    /**
     * Dialog builder from within onCreateDialog
     *
     * @param builder MaterialDialog.Builder
     * @param args    arguments from any passed bundle
     */
    protected abstract void buildDialog(MaterialDialog.Builder builder, @Nullable Bundle args);

    /**
     * Main method for launching dialog; hides opened dialogs with same tag
     */
    public void show(@NonNull FragmentActivity activity, T param) {
        Fragment frag = activity.getSupportFragmentManager().findFragmentByTag(getFragmentTag());
        if (frag != null) ((CapsuleDialog) frag).dismiss();
        initialize(activity, param);
    }

    /**
     * Basic wrapper that opens the dialog with the appropriate tag
     */
    protected void showDialog(FragmentManager manager) {
        show(manager, getFragmentTag());
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(getContext());
        buildDialog(builder, getArguments());
        return builder.build();
    }

}
