package com.pitchedapps.capsule.library.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pitchedapps.capsule.library.item.UniversalItem;
import com.pitchedapps.capsule.library.item.UniversalViewHolder;

import java.util.Collections;
import java.util.List;

/**
 * Created by Allan Wang on 2016-12-21.
 */

public abstract class UniversalAdapter<T extends UniversalItem<V>, V extends UniversalViewHolder<T>> extends RecyclerView.Adapter<V> {

    private Context context;
    private List<T> mList = Collections.emptyList(); // the data entries to display

    public UniversalAdapter(Context context, List<T> data, Object... o) {
        this.context = context;
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

    /**
     * this creates new view holders for the currently visible table rows
     * (invoked by layout manager)
     */
    @Override
    public V onCreateViewHolder(ViewGroup parent, int position) {
        final T universalItem = mList.get(position);
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(universalItem.getLayoutId(), parent, false);
        return universalItem.createViewHolder(parent.getContext(), view, universalItem, position);
    }

    /**
     * bind data to a new view holder, or recycle a view holder by replacing its contents when it goes offscreen (invoked by layout manager)
     * view holder (defined below) wraps several text views for ease of use
     */
    @Override
    public void onBindViewHolder(V vh, int position) {
        vh.bind(mList.get(position));
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
