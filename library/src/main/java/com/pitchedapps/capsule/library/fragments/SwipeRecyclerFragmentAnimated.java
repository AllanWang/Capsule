package com.pitchedapps.capsule.library.fragments;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pitchedapps.capsule.library.item.CapsuleViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allan Wang on 2016-12-21.
 */

public abstract class SwipeRecyclerFragmentAnimated<T, V extends CapsuleViewHolder> extends SwipeRecyclerFragment<T, V> {

    @Override
    @CallSuper
    protected void onRecyclerViewBound(RecyclerView recyclerView) {
        recyclerView.setItemAnimator(getRecyclerAnimator());
    }

    protected abstract RecyclerView.ItemAnimator getRecyclerAnimator();

    protected abstract void updateList(List<T> oldList);

    protected void updateListShell() {
        final List<T> list = cAdapter.getList();
        cAdapter.updateList(null);
        updateList(list);
    }

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
