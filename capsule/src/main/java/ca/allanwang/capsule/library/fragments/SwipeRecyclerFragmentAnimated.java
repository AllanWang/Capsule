package ca.allanwang.capsule.library.fragments;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ca.allanwang.capsule.library.item.CapsuleViewHolder;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Created by Allan Wang on 2016-12-21.
 * <p>
 * Simplifies ItemAnimator and refresh functionality
 */

public abstract class SwipeRecyclerFragmentAnimated<T, V extends CapsuleViewHolder> extends SwipeRecyclerFragment<T, V> {

    @Override
    @CallSuper
    protected void onRecyclerViewBound(RecyclerView recyclerView) {
        recyclerView.setItemAnimator(getRecyclerAnimator());
    }

    /**
     * Specify the itemAnimator
     * Defaults to slide up
     *
     * @return item animator
     */
    protected RecyclerView.ItemAnimator getRecyclerAnimator() {
        return new SlideInUpAnimator(new FastOutLinearInInterpolator());
    }

    /**
     * Create new list and update cAdapter
     *
     * @param oldList previous list in the adapter
     */
    protected abstract void updateList(List<T> oldList);

    protected void updateListShell() {
        final List<T> list = cAdapter.getList();
        cAdapter.updateList(null);
        updateList(list);
    }

    /**
     * By default, we'll update the list as soon as possible
     *
     * @param view               fragment view
     * @param savedInstanceState fragment saved state
     */
    @Override
    @CallSuper
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        updateList(new ArrayList<T>());
    }

    protected void showRefresh() {
        cSwipeRefreshRecyclerView.setRefreshing(true);
    }

    protected void hideRefresh() {
        cSwipeRefreshRecyclerView.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        updateListShell();
    }

}
