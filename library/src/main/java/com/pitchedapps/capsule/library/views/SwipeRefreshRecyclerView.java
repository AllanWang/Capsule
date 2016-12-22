package com.pitchedapps.capsule.library.views;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Allan Wang on 2016-12-21.
 */

public class SwipeRefreshRecyclerView extends SwipeRefreshLayout {

    private RecyclerView mInternalRecyclerView = null;

    public SwipeRefreshRecyclerView(Context context) {
        super(context);
    }

    public SwipeRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setInternalRecyclerView(RecyclerView internalRecyclerView) {
        mInternalRecyclerView = internalRecyclerView;
    }

    public RecyclerView getInternalRecyclerView() {
        return mInternalRecyclerView;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mInternalRecyclerView.canScrollVertically(-1)) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
