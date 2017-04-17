package ca.allanwang.capsule.library.swiperecyclerview.managers;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

import ca.allanwang.capsule.library.swiperecyclerview.interfaces.IScrolling;

/**
 * Created by Allan Wang on 2017-03-09.
 */

public class SLinearLayoutManager extends LinearLayoutManager implements IScrolling {
    private boolean isScrollEnabled = true;

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
    public boolean canScrollVertically() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollVertically();
    }
}
