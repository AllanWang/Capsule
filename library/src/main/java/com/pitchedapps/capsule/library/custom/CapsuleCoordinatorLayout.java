package com.pitchedapps.capsule.library.custom;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Allan Wang on 2016-09-05.
 */
public class CapsuleCoordinatorLayout extends CoordinatorLayout {

    private boolean allowScroll = true;

    public CapsuleCoordinatorLayout(Context context) {
        super(context);
    }

    public CapsuleCoordinatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CapsuleCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return allowScroll && super.onStartNestedScroll(child, target, nestedScrollAxes);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return allowScroll && super.onInterceptTouchEvent(ev);
    }

    public boolean isScrollAllowed() {
        return allowScroll;
    }

    public void setScrollAllowed(boolean allowScroll) {
        this.allowScroll = allowScroll;
    }
}
