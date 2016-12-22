package com.pitchedapps.capsule.library.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pitchedapps.capsule.library.R;
import com.pitchedapps.capsule.library.adapters.CapsuleAdapter;
import com.pitchedapps.capsule.library.item.CapsuleViewHolder;
import com.pitchedapps.capsule.library.views.SwipeRefreshRecyclerView;

/**
 * Created by Allan Wang on 2016-12-21.
 */

public abstract class SwipeRecyclerFragment<T, V extends CapsuleViewHolder> extends CapsuleFragment implements SwipeRefreshRecyclerView.OnRefreshListener {

    protected SwipeRefreshRecyclerView cSwipeRefreshRecyclerView;
    protected CapsuleAdapter<T, V> cAdapter;

    @Override
    @CallSuper
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_swipe_recycler, container, false);
        cSwipeRefreshRecyclerView = ((SwipeRefreshRecyclerView) v.findViewById(R.id.swipe_recycler));
        cSwipeRefreshRecyclerView.setOnRefreshListener(this);
        cAdapter = getAdapter(getContext());
        RecyclerView rv = cAdapter.bindRecyclerView(v, R.id.inner_recycler);
        onRecyclerViewBound(rv);
        cSwipeRefreshRecyclerView.setInternalRecyclerView(rv);
        return v;
    }

    protected void onRecyclerViewBound(RecyclerView recyclerView) {

    }

    protected abstract CapsuleAdapter<T, V> getAdapter(Context context);

    protected void showRefresh() {
        cSwipeRefreshRecyclerView.setRefreshing(true);
    }

    protected void hideRefresh() {
        cSwipeRefreshRecyclerView.setRefreshing(false);
    }

}
