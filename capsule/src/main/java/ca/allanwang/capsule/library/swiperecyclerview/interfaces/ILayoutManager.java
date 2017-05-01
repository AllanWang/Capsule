package ca.allanwang.capsule.library.swiperecyclerview.interfaces;

/**
 * Created by Allan Wang on 2017-03-09.
 */

public interface ILayoutManager {
    void setScrollEnabled(boolean flag);

    void setSmoothScrollDuration(ScrollSpeed scrollSpeed);

    interface ScrollSpeed {
        float calculateSpeedPerPixel(float originalSpeed);
    }
}
