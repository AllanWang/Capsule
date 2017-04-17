package ca.allanwang.capsule.library.swiperecyclerview.events;

import android.support.annotation.LayoutRes;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Allan Wang on 2017-02-23.
 */

public class UAEvents {
    public interface OnClickEvent {
        void onClick(View v, @LayoutRes int layoutId, int position);
    }

    public interface OnLongClickEvent {
        /**
         * Called when a view has been clicked and held.
         *
         * @param v The view that was clicked and held.
         * @return true if the callback consumed the long click, false otherwise.
         */
        boolean onLongClick(View v, @LayoutRes int layoutId, int position);
    }

    public interface OnTouchEvent {
        /**
         * Called when a touch event is dispatched to a view. This allows listeners to
         * get a chance to respond before the target view.
         *
         * @param v     The view the touch event has been dispatched to.
         * @param event The MotionEvent object containing full information about
         *              the event.
         * @return True if the listener has consumed the event, false otherwise.
         */
        boolean onTouch(View v, MotionEvent event, @LayoutRes int layoutId, int position);
    }
}
