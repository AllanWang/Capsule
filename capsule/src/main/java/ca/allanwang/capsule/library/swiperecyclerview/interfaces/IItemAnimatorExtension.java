package ca.allanwang.capsule.library.swiperecyclerview.interfaces;

/**
 * Created by Allan Wang on 2017-03-13.
 */
public interface IItemAnimatorExtension {

    /**
     * Allows for many animators without constantly switching between them
     * Make sure you add items through lists or items; arrays are marked final and do not trigger events
     *
     * @param toTop    are items added to the start of the adapter
     * @param toBottom are items added to the end of the adapter
     * @param isEmpty  is adapter currently empty
     */
    void triggerAdd(boolean toTop, boolean toBottom, boolean isEmpty);

}
