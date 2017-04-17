package ca.allanwang.capsule.library.swiperecyclerview.interfaces;

/**
 * Created by Allan Wang on 2017-02-24.
 */

public class ISwipeRecycler {

    /**
     * Default OnRefreshListener with status emitter
     */
    public interface OnRefreshListener {
        void onRefresh(OnRefreshStatus statusEmitter);
    }

    /**
     * Reusable callbacks when an onRefresh action is called
     */
    public interface OnRefreshStatus {
        void onSuccess();
        void onFailure();
    }
}
