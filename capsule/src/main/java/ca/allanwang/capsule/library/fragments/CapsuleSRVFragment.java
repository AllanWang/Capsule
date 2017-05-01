package ca.allanwang.capsule.library.fragments;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.fastadapter.IItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import ca.allanwang.capsule.library.R;
import ca.allanwang.capsule.library.event.CFabEvent;
import ca.allanwang.capsule.library.event.RefreshEvent;
import ca.allanwang.capsule.library.swiperecyclerview.SwipeRecyclerView;
import ca.allanwang.capsule.library.swiperecyclerview.adapters.AnimationAdapter;
import ca.allanwang.capsule.library.swiperecyclerview.animators.SlidingAnimator;
import ca.allanwang.capsule.library.swiperecyclerview.interfaces.ISwipeRecycler;

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
        View v = inflater.inflate(getLayout(), container, false);

        mAdapter = createAdapter();
        configAdapter(mAdapter);

        configSRV(mSRV = SwipeRecyclerView.hook(v, R.id.swipe_recycler)
                .setAdapter(mAdapter, getNumColumns())
                .setOnRefreshListener(this)
                .setItemAnimator(new SlidingAnimator()));
        return v;
    }

    protected int getNumColumns() {
        return 1;
    }

    protected
    @LayoutRes
    int getLayout() {
        return R.layout.capsule_srv_swipe_recycler_view;
    }

    @Subscribe
    public void refreshEvent(RefreshEvent event) {
        if (event == null || event.titleId != getTitleId()) return;
        if (event.silentRefresh) refreshSilently();
        else refresh();
    }

    @Override
    @CallSuper
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    @CallSuper
    public void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
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

    public SwipeRecyclerView getSRV() {
        return mSRV;
    }

    public LinearLayoutManager getLayoutManager() {
        return (LinearLayoutManager) mSRV.getRecyclerView().getLayoutManager();
    }

    public boolean isFirstItemCompletelyVisible() {
        return getLayoutManager().findFirstCompletelyVisibleItemPosition() == 0;
    }

}
