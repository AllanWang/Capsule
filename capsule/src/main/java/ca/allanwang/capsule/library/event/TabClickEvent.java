package ca.allanwang.capsule.library.event;

/**
 * Created by Allan Wang on 2017-04-14.
 */

public class TabClickEvent {

    public final int prevPosition, newPosition;

    public TabClickEvent(int prevPosition, int newPosition) {
        this.prevPosition = prevPosition;
        this.newPosition = newPosition;
    }
}
