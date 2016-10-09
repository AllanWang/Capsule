package com.pitchedapps.capsule.library.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by Allan Wang on 2016-10-09.
 */

public abstract class CapsuleDialog<T> extends DialogFragment {

    /**
     * Retrieves tag
     * @return tag
     */
    public abstract String getFragmentTag();

    /**
     * Loads the dialog; the most basic implementation would be
     * to just call showDialog(); no param? Try CapsuleDialogBasic
     */
    public abstract void initialize(final T param);

    /**
     * Dialog builder from within onCreateDialog
     * @param builder MaterialDialog.Builder
     * @param args arguments from any passed bundle
     */
    public abstract void buildDialog(MaterialDialog.Builder builder, @Nullable Bundle args);

    /**
     * Main method for launching dialog; hides opened dialogs with same tag
     */
    public void show(T param) {
        Fragment frag = getActivity().getSupportFragmentManager().findFragmentByTag(getFragmentTag());
        if (frag != null) ((CapsuleDialog) frag).dismiss();
        initialize(param);
    }

    /**
     * Basic wrapper that opens the dialog with the appropriate tag
     */
    protected void showDialog() {
        show(getActivity().getSupportFragmentManager(), getFragmentTag());
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(getActivity());
        buildDialog(builder, getArguments());
        return builder.build();
    }

}
