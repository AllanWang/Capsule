package ca.allanwang.capsule.library.swiperecyclerview.managers;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

import ca.allanwang.capsule.library.swiperecyclerview.interfaces.IScrolling;

/**
 * Created by Allan Wang on 2017-03-09.
 */

public class SGridLayoutManager extends GridLayoutManager implements IScrolling {
    private boolean isScrollEnabled = true;

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
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }
}
