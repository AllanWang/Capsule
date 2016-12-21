package com.pitchedapps.capsule.library.adapters;

import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Collections;
import java.util.List;

/**
 * Created by Allan Wang on 2016-12-21.
 */

public abstract class CapsuleAdapter<T, V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V> {

    public RecyclerView bindRecyclerView(View view, @IdRes int recyclerviewId) {
        RecyclerView rv = (RecyclerView) view.findViewById(recyclerviewId);
        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rv.setAdapter(this);
        return rv;
    }

    private List<T> mList = Collections.emptyList(); // the data entries to display

    public CapsuleAdapter(List<T> data) {
        if (data != null) mList = data;
    }

    public void updateList(List<T> data) {
        if (data != null) mList = data;
        else mList.clear();
        notifyItemRangeChanged(0, getItemCount()); //To allow for animations
    }

    public void updateItem(int index, T item) {
        if (index >= mList.size()) {
            mList.add(item);
            notifyItemChanged(getItemCount() - 1);
        } else {
            mList.add(index, item);
            notifyItemChanged(index);
        }
    }

    public void addItem(T item) {
        mList.add(item);
        notifyItemChanged(getItemCount() - 1);
    }

    public T getItem(int position) {
        if (position >= getItemCount()) return null;
        return mList.get(position);
    }

    /**
     * returns the size of the adapter's data set (invoked by layout manager)
     */
    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
