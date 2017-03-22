package ca.allanwang.capsule.library.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.fastadapter.IItem;

import org.greenrobot.eventbus.Subscribe;

import ca.allanwang.capsule.library.R;
import ca.allanwang.capsule.library.event.CFabEvent;
import ca.allanwang.capsule.library.event.RefreshEvent;
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
    protected SwipeRecyclerView mSRV;

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

        mAdapter = createAdapter();
        configAdapter(mAdapter);

        configSRV(mSRV = SwipeRecyclerView.hook(v, R.id.swipe_recycler)
                .setAdapter(mAdapter)
                .setOnRefreshListener(this)
                .setItemAnimator(new SlidingAnimator()));
        return v;
    }

    @Subscribe
    public void refreshEvent(RefreshEvent event) {
        if (event == null || event.titleId != getTitleId()) return;
        if (event.silentRefresh) refreshSilently();
        else refresh();
    }

    /**
     * Wrapper for initializing the adapter
     *
     * @return new Animation Adapter
     */
    protected AnimationAdapter<I> createAdapter() {
        return new AnimationAdapter<>();
    }

    protected abstract void configAdapter(AnimationAdapter<I> adapter);

    protected abstract void configSRV(SwipeRecyclerView srv);

    public void refresh() {
        if (mSRV != null)
            mSRV.refresh();
    }

    public void refreshSilently() {
        if (mSRV != null)
            mSRV.refreshSilently();
    }

}
