package ca.allanwang.capsule.library.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.fastadapter.IItem;

import ca.allanwang.capsule.library.R;
import ca.allanwang.capsule.library.event.CFabEvent;
import ca.allanwang.swiperecyclerview.library.SwipeRecyclerView;
import ca.allanwang.swiperecyclerview.library.adapters.AnimationAdapter;
import ca.allanwang.swiperecyclerview.library.animators.SlidingAnimator;
import ca.allanwang.swiperecyclerview.library.interfaces.ISwipeRecycler;

/**
 * Created by Allan Wang on 2017-03-18.
 * Abstraction for SRV fragments
 */

public abstract class CapsuleSRVFragment<I extends IItem> extends CapsuleFragment implements ISwipeRecycler.OnRefreshListener {

    protected AnimationAdapter<I> mAdapter;

    @Nullable
    @Override
    protected CFabEvent updateFab() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.swipe_recycler_view, container, false);

        mAdapter = new AnimationAdapter<>();
        configAdapter(mAdapter);

        configSRV(SwipeRecyclerView.hook(v, R.id.swipe_recycler)
                .setAdapter(mAdapter)
                .setOnRefreshListener(this)
                .setItemAnimator(new SlidingAnimator()));
        return v;
    }

    protected abstract void configAdapter(AnimationAdapter<I> adapter);
    protected abstract void configSRV(SwipeRecyclerView srv);

}
