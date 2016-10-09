package com.pitchedapps.capsule.library.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by Allan Wang on 2016-10-09.
 */

public abstract class BasicCapsuleDialog extends DialogFragment {

    /**
     * Retrieves tag
     * @return tag
     */
    public abstract String getFragmentTag();

    /**
     * Dialog builder from within onCreateDialog
     * @param builder MaterialDialog.Builder
     */
    public abstract void buildDialog(MaterialDialog.Builder builder);

    /**
     * Main method for launching dialog; hides opened dialogs with same tag
     */
    public void show() {
        Fragment frag = getActivity().getSupportFragmentManager().findFragmentByTag(getFragmentTag());
        if (frag != null) ((BasicCapsuleDialog) frag).dismiss();
        showDialog();
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
        buildDialog(builder);
        return builder.build();
    }

}
