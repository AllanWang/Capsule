package com.pitchedapps.capsule.library.item;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Allan Wang on 2016-12-21.
 */

public abstract class UniversalItem<V extends RecyclerView.ViewHolder> {

    public abstract
    @LayoutRes
    int getLayoutId();

    public abstract int getType();

//    public UniversalItem(int type) {
//        this.type = type;
//    }

    public abstract V createViewHolder(Context context, View view, UniversalItem item, int position);

}
