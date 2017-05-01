package ca.allanwang.capsule.library.changelog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.XmlRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import ca.allanwang.capsule.library.R;

/**
 * @author Allan Wang
 */
public class ChangelogDialog extends DialogFragment {

    private static final String ITEM_TAG = "changelog_items";
    private static final String DIALOG_TAG = "capsule_changelog_dialog";

    private OnChangelogNeutralButtonClick neutralCallback = null;

    public static void show(@NonNull final FragmentActivity activity, @XmlRes final int xmlRes) {
        show(activity, xmlRes, null);
    }

    /**
     * Setup for ChangelogDialog
     *
     * @param activity        activity for context
     * @param xmlRes          xmlRes of Changelog file
     * @param neutralCallback optional callback & string for neutral button
     */
    public static void show(@NonNull final FragmentActivity activity, @XmlRes final int xmlRes,
                            @Nullable final OnChangelogNeutralButtonClick neutralCallback) {
        final Handler mHandler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final ArrayList<ChangelogXmlParser.ChangelogItem> items = ChangelogXmlParser
                        .parse(activity, xmlRes);
                mHandler.post(new TimerTask() {
                    @Override
                    public void run() {
                        ChangelogDialog f = new ChangelogDialog()
                                .setNeutralCallback(neutralCallback);
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
        builder.title(R.string.capsule_changelog_dialog_title)
                .positiveText(R.string.capsule_great);
        if (getArguments() == null || !getArguments().containsKey(ITEM_TAG)) {
            builder.content(R.string.capsule_empty_changelog);
        } else {
            List<ChangelogXmlParser.ChangelogItem> items =
                    getArguments().getParcelableArrayList(ITEM_TAG);
            builder.adapter(new ChangelogAdapter(items), null);
        }
        if (neutralCallback != null) {
            builder.neutralText(neutralCallback.getNeutralText());
            builder.onNeutral(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    neutralCallback.onNeutralButtonClick(dialog);
                }
            });
        }
        return builder.build();
    }

    public ChangelogDialog setNeutralCallback(OnChangelogNeutralButtonClick neutralCallback) {
        this.neutralCallback = neutralCallback;
        return this;
    }

    public interface OnChangelogNeutralButtonClick {
        @StringRes
        int getNeutralText();

        void onNeutralButtonClick(@NonNull MaterialDialog dialog);
    }

}