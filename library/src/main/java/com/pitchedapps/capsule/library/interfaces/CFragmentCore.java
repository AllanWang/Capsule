package com.pitchedapps.capsule.library.interfaces;

import android.support.annotation.StringRes;
import android.view.View;

/**
 * Created by Allan Wang on 2016-10-09.
 * <p>
 * Contains list of methods used by CapsuleActivity
 */

public interface CFragmentCore {

    void onFabClick(View v);

    @StringRes
    int getTitleId();

}
