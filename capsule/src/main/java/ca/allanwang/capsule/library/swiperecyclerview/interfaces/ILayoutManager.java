package ca.allanwang.capsule.library.swiperecyclerview.interfaces;

/**
 * Created by Allan Wang on 2017-03-09.
 */

public interface ILayoutManager {
    void setScrollEnabled(boolean flag);

    void setSmoothScrollDuration(ScrollTime scrollTime);

    interface ScrollTime {
        int calculateTimeForScrolling(int dx, int originalDuration);
    }
}
