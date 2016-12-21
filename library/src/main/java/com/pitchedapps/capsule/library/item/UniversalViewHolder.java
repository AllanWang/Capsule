package com.pitchedapps.capsule.library.item;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Allan Wang on 2016-12-21.
 */

public abstract class UniversalViewHolder<T extends UniversalItem> extends RecyclerView.ViewHolder {

    public UniversalViewHolder(Context c, View itemView, T item) {
        super(itemView);
    }

    public abstract void bind(T item);
}
