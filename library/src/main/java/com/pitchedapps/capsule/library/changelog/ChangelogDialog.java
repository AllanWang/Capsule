package com.pitchedapps.capsule.library.changelog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.XmlRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.pitchedapps.capsule.library.R;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

/**
 * @author Allan Wang
 */
public class ChangelogDialog extends DialogFragment {

    private static final String ITEM_TAG = "changelog_items", DIALOG_TAG = "capsule_changelog_dialog";

    public static void show(@NonNull final FragmentActivity activity, @XmlRes final int xmlRes) {
        final Handler mHandler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final ArrayList<ChangelogXmlParser.ChangelogItem> items = ChangelogXmlParser.parse(activity, xmlRes);
                mHandler.post(new TimerTask() {
                    @Override
                    public void run() {
                        ChangelogDialog f = new ChangelogDialog();
                        if (!items.isEmpty()) {
                            Bundle args = new Bundle();
                            args.putParcelableArrayList(ITEM_TAG, items);
                            f.setArguments(args);
                        }
                        f.show(activity.getSupportFragmentManager(), DIALOG_TAG);
                    }
                });
            }
        }).start();
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(getContext());
        builder.title(R.string.changelog_dialog_title)
                .positiveText(R.string.great);

        if (getArguments() == null || !getArguments().containsKey(ITEM_TAG)) {
            builder.content(R.string.empty_changelog);
        } else {
            List<ChangelogXmlParser.ChangelogItem> items = getArguments().getParcelableArrayList(ITEM_TAG);
            builder.adapter(new ChangelogAdapter(items), null);
        }
        return builder.build();
    }

}