package ca.allanwang.capsule.library.views;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Allan Wang on 2016-12-21.
 * <p>
 * Modified SwipeRefreshLayout to handle touch events for inner recyclerView
 */
public class SwipeRefreshRecyclerView extends SwipeRefreshLayout {

    private RecyclerView mInternalRecyclerView = null;

    public SwipeRefreshRecyclerView(Context context) {
        super(context);
    }

    public SwipeRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerView getInternalRecyclerView() {
        return mInternalRecyclerView;
    }

    public void setInternalRecyclerView(RecyclerView internalRecyclerView) {
        mInternalRecyclerView = internalRecyclerView;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return !mInternalRecyclerView.canScrollVertically(-1) && super.onInterceptTouchEvent(ev);
    }
}
