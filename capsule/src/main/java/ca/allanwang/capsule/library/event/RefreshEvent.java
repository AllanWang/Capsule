package ca.allanwang.capsule.library.event;

import android.support.annotation.StringRes;

/**
 * Created by Allan Wang on 22/03/2017.
 */

public class RefreshEvent {
    public final
    @StringRes
    int titleId;
    public final boolean silentRefresh;

    public RefreshEvent(@StringRes int titleId, boolean silentRefresh) {
        this.titleId = titleId;
        this.silentRefresh = silentRefresh;
    }

    public RefreshEvent(@StringRes int titleId) {
        this.titleId = titleId;
        this.silentRefresh = true;
    }
}
