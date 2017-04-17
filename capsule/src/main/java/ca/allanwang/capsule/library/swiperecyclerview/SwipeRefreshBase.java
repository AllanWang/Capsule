package ca.allanwang.capsule.library.swiperecyclerview;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Allan Wang on 2017-02-23.
 */

public class SwipeRefreshBase extends SwipeRefreshLayout {

    private ISwipeRefresh mISwipe;

    interface ISwipeRefresh {
        /**
         * Dictates whether touch should be received & consumed
         *
         * @param ev MotionEvent triggered
         * @return true to consume, false otherwise
         */
        boolean shouldConsumeTouch(MotionEvent ev);
    }

    public SwipeRefreshBase(Context context) {
        super(context);
    }

    public SwipeRefreshBase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    void setISwipe(ISwipeRefresh iSwipe) {
        mISwipe = iSwipe;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mISwipe.shouldConsumeTouch(ev) && super.onInterceptTouchEvent(ev);
    }
}
