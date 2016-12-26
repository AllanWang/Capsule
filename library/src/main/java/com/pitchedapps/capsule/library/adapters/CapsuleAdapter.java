package com.pitchedapps.capsule.library.adapters;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.pitchedapps.capsule.library.item.CapsuleViewHolder;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.internal.ViewHelper;

/**
 * Created by Allan Wang on 2016-12-21.
 * <p>
 * Base adapter with helper methods to add to the list
 */

public abstract class CapsuleAdapter<T, V extends CapsuleViewHolder> extends RecyclerView.Adapter<V> {

    private int mDuration = 300;
    private Interpolator mInterpolator = new LinearInterpolator();
    private int mLastPosition = -1;
    protected Animations mAnimations;

    private boolean isFirstOnly = true;

    public RecyclerView bindRecyclerView(View view, @IdRes int recyclerviewId) {
        RecyclerView rv = (RecyclerView) view.findViewById(recyclerviewId);
        return bindRecyclerView(rv);
    }

    public RecyclerView bindRecyclerView(@NonNull RecyclerView rv) {
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        rv.setAdapter(this);
        return rv;
    }

    /**
     * Optional animations for new views
     * call from onBindViewHolder
     * @param holder item to be animated
     */
    protected void bindAnimation(V holder) {
        int adapterPosition = holder.getAdapterPosition();
        if (!isFirstOnly || adapterPosition > mLastPosition) {
            for (Animator anim : mAnimations.getAnimators(holder.itemView)) {
                anim.setDuration(mDuration).start();
                anim.setInterpolator(mInterpolator);
            }
            mLastPosition = adapterPosition;
        } else {
            ViewHelper.clear(holder.itemView);
        }
    }

    protected class Animations {
        public void setDuration(int duration) {
            mDuration = duration;
        }

        public Animations setInterpolator(Interpolator interpolator) {
            mInterpolator = interpolator;
            return this;
        }

        public Animations setStartPosition(int start) {
            mLastPosition = start;
            return this;
        }

        /**
         * Animation for new items
         * Default is fade in
         * @param view to animate
         * @return
         */
        protected Animator[] getAnimators(View view) {
            return new Animator[] { ObjectAnimator.ofFloat(view, "alpha", 0f, 1f) };
        }

        public Animations setFirstOnly(boolean firstOnly) {
            isFirstOnly = firstOnly;
            return this;
        }
    }

    private List<T> mList = new ArrayList<>(); // the data entries to display

    public CapsuleAdapter(List<T> data) {
        if (data != null) mList = data;
    }

    public void updateList(List<T> data) {
        final int initialCount = getItemCount();
        if (data != null) mList = data;
        else mList.clear();
        notifyItemRangeChanged(0, getItemCount()); //To allow for animations
        if (initialCount > getItemCount()) {
            notifyItemRangeRemoved(getItemCount(), initialCount - getItemCount());
        }
    }

    public void updateItem(int index, T item) {
        if (index >= mList.size()) {
            addItem(item);
        } else {
            mList.add(index, item);
            notifyItemChanged(index);
        }
    }

    public void addItem(@NonNull T item) {
        mList.add(item);
        notifyItemInserted(getItemCount() - 1);
    }

    public void addItem(int index, @NonNull T item) {
        if (index >= getItemCount()) addItem(item);
        else {
            mList.add(index, item);
            notifyItemInserted(index);
        }
    }

    public void addItems(@NonNull List<T> items) {
        int initialSize = getItemCount();
        mList.addAll(items);
        notifyItemRangeInserted(initialSize, items.size());
    }

    public void addItems(int index, @NonNull List<T> items) {
        if (index >= getItemCount()) addItems(items);
        else {
            mList.addAll(index, items);
            notifyItemRangeInserted(index, items.size());
        }
    }

    public void removeItem(@NonNull T item) {
        mList.add(item);
        notifyItemRemoved(getItemCount() - 1);
    }

    public void removeItem(int index, @NonNull T item) {
        if (index >= getItemCount()) addItem(item);
        else {
            mList.add(index, item);
            notifyItemRemoved(index);
        }
    }

    public T getItem(int position) {
        if (position >= getItemCount()) return null;
        return mList.get(position);
    }

    public List<T> getList() {
        return mList;
    }

    @Override
    public V onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflate(parent, getLayoutRes(viewType));
        return inflateViewHolder(v, getLayoutRes(viewType));
    }

    /**
     * Return layoutRes for view at given position
     * Can be different for different positions
     *
     * @param position
     * @return
     */
    protected abstract
    @LayoutRes
    int getLayoutRes(int position);

    /**
     * Create ViewHolder (within onCreateViewHolder)
     * eg return new ViewHolder(view, layoutId);
     *
     * @param view     inflated with layoutId
     * @param layoutId the id of the inflated layout
     * @return sub ViewHolder
     */
    protected abstract
    @NonNull
    V inflateViewHolder(View view, @LayoutRes int layoutId);

    private View inflate(ViewGroup viewGroup, @LayoutRes int id) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(id, viewGroup, false);
    }

    /**
     * returns the size of the adapter's data set (invoked by layout manager)
     */
    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
