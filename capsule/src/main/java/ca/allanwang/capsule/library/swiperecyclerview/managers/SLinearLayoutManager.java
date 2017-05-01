package ca.allanwang.capsule.library.swiperecyclerview.managers;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

import ca.allanwang.capsule.library.swiperecyclerview.interfaces.ILayoutManager;

/**
 * Created by Allan Wang on 2017-03-09.
 */

public class SLinearLayoutManager extends LinearLayoutManager implements ILayoutManager {
    private boolean isScrollEnabled = true;
    private int duration = -1; //One time switch

    public SLinearLayoutManager(Context context) {
        super(context);
    }

    public SLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public SLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    @Override
    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public void setSmoothScrollDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller linearSmoothScroller =
                new LinearSmoothScroller(recyclerView.getContext()) {
                    @Override
                    protected int calculateTimeForScrolling(int dx) {
                        if (duration == -1) return super.calculateTimeForScrolling(dx);
                        return duration;
                    }
                };
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
        duration = -1; //Reset
    }

    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }

}
