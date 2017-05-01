package ca.allanwang.capsule.library.swiperecyclerview.managers;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import ca.allanwang.capsule.library.swiperecyclerview.interfaces.ILayoutManager;

/**
 * Created by Allan Wang on 2017-03-09.
 */

public class SGridLayoutManager extends GridLayoutManager implements ILayoutManager {
    private boolean isScrollEnabled = true;
    private ScrollTime scrollTime; //One time switch

    public SGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public SGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public SGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public void setSmoothScrollDuration(ScrollTime scrollTime) {
        this.scrollTime = scrollTime;
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller linearSmoothScroller =
                new LinearSmoothScroller(recyclerView.getContext()) {
                    @Override
                    protected int calculateTimeForScrolling(int dx) {
                        if (scrollTime != null) {
                            int duration = scrollTime.calculateTimeForScrolling(dx, super.calculateTimeForScrolling(dx));
                            scrollTime = null; //Reset
                            return duration;
                        }
                        return super.calculateTimeForScrolling(dx);
                    }
                };
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }

    @Override
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }
}
