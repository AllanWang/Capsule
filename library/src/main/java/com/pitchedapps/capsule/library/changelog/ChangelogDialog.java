package com.pitchedapps.capsule.library.changelog;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.XmlRes;
import android.support.v4.app.FragmentActivity;

import com.afollestad.materialdialogs.MaterialDialog;
import com.pitchedapps.capsule.library.R;
import com.pitchedapps.capsule.library.dialog.CapsuleDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

/**
 * @author Allan Wang
 */
public class ChangelogDialog extends CapsuleDialog<Integer> {

    //Just for the annotation
    @Override
    public void show(@NonNull FragmentActivity activity, @XmlRes Integer xmlRes) {
        super.show(activity, xmlRes);
    }

    /**
     * Retrieves tag
     *
     * @return tag
     */
    @Override
    protected String getFragmentTag() {
        return "capsule_changelog_dialog";
    }

    /**
     * Loads the dialog; the most basic implementation would be
     * to just call showDialog()
     */
    @Override
    protected void initialize(final FragmentActivity activity, @XmlRes final Integer xmlRes) {
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
                            args.putParcelableArrayList("changelog_items", items);
                            f.setArguments(args);
                        }
                        f.showDialog(activity.getSupportFragmentManager());
                    }
                });
            }
        }).start();
    }

    /**
     * Dialog builder from within onCreateDialog
     *
     * @param builder MaterialDialog.Builder
     * @param args    arguments from any passed bundle
     */
    @Override
    protected void buildDialog(MaterialDialog.Builder builder, @Nullable Bundle args) {
        builder.title(R.string.changelog_dialog_title)
                .positiveText(R.string.great);

        if (args == null || !args.containsKey("changelog_items")) {
            builder.content(R.string.empty_changelog);
        } else {
            List<ChangelogXmlParser.ChangelogItem> items = args.getParcelableArrayList("changelog_items");
            builder.adapter(new ChangelogAdapter(items), null);
        }
    }

}