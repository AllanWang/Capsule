package ca.allanwang.capsule.library.activities;

import android.support.annotation.NonNull;

import ca.allanwang.capsule.library.interfaces.CCallback;
import ca.allanwang.capsule.library.utils.CPrefs;

/**
 * Created by Allan Wang on 2016-10-30.
 * <p>
 * Activity containing some useful methods that aren't critical to the activity
 */

abstract class UtilsActivity extends ViewActivity {

    protected void onVersionUpdate(int newVersionCode, @NonNull CCallback callback) {
        CPrefs prefs = new CPrefs(this);
        if (newVersionCode > prefs.getVersionCode()) {
            callback.onResult();
        }
        prefs.setVersionCode(newVersionCode);
    }
}
