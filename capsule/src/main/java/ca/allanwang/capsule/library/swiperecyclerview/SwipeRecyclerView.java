package ca.allanwang.capsule.library.swiperecyclerview;

import android.content.Context;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;

import ca.allanwang.capsule.library.R;
import ca.allanwang.capsule.library.logging.CLog;
import ca.allanwang.capsule.library.swiperecyclerview.adapters.AnimationAdapter;
import ca.allanwang.capsule.library.swiperecyclerview.animators.SlidingAnimator;
import ca.allanwang.capsule.library.swiperecyclerview.interfaces.ILayoutManager;
import ca.allanwang.capsule.library.swiperecyclerview.interfaces.ISwipeRecycler;
import ca.allanwang.capsule.library.swiperecyclerview.managers.SGridLayoutManager;
import ca.allanwang.capsule.library.swiperecyclerview.managers.SLinearLayoutManager;

/**
 * Created by Allan Wang on 2017-02-06.
 */

public class SwipeRecyclerView extends FrameLayout implements SwipeRefreshBase.ISwipeRefresh, SwipeRefreshLayout.OnRefreshListener {

    private Context mContext;
    private SwipeRefreshBase mSwipe;
    private RecyclerView mRecycler;

    private ISwipeRecycler.OnRefreshListener mRefreshListener;
    private ISwipeRecycler.OnRefreshStatus mRefreshStatus = getDefaultRefreshStatus();
    private SilentRefreshListener mSilentRefreshListener = getDefaultSilentRefreshListener();

    /**
     * Generate default refresh callbacks
     *
     * @return OnRefreshStatus
     */
    private ISwipeRecycler.OnRefreshStatus getDefaultRefreshStatus() {
        return new ISwipeRecycler.OnRefreshStatus() {
            @Override
            public void onSuccess() {
                hideRefresh();
            }

            @Override
            public void onFailure() {
                hideRefresh();
            }
        };
    }

    public SwipeRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public SwipeRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SwipeRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public static SwipeRecyclerView hook(View view, @IdRes int id) {
        return (SwipeRecyclerView) view.findViewById(id);
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    public SwipeRecyclerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.capsule_srv_base, this);
        mSwipe = (SwipeRefreshBase) findViewById(R.id.srv_swipe);
        mSwipe.setISwipe(this); //handle touch events
        mSwipe.setOnRefreshListener(this);
        disableRefresh(); //disable refresh until listener is added
        mRecycler = (RecyclerView) findViewById(R.id.srv_recycler);
    }

    public SwipeRecyclerView enableRefresh() {
        if (mRefreshListener == null) {
            CLog.w("No Refresh Listener added; disabling refresh pull down");
            return disableRefresh();
        }
        mSwipe.setEnabled(true);
        return this;
    }

    public SwipeRecyclerView disableRefresh() {
        mSwipe.setEnabled(false);
        return this;
    }

    public SwipeRecyclerView setRefreshing(boolean refreshing) {
        mSwipe.setRefreshing(refreshing);
        return this;
    }

    public SwipeRecyclerView showRefresh() {
        return setRefreshing(true);
    }

    public SwipeRecyclerView hideRefresh() {
        return setRefreshing(false);
    }

    public SwipeRecyclerView setItemAnimator(RecyclerView.ItemAnimator animator) {
        mRecycler.setItemAnimator(animator);
//        mRecycler.setRecyclerListener(new RecyclerView.RecyclerListener() {
//            @Override
//            public void onViewRecycled(RecyclerView.ViewHolder holder) {
//
//            }
//        });
        return this;
    }

    public SwipeRecyclerView setDefaultItemAnimator() {
        return setItemAnimator(new SlidingAnimator());
    }

    /**
     * Sets adapter and sets layout manager if not present.
     *
     * @param adapter the adapter
     * @return SRV
     */
    public SwipeRecyclerView setAdapter(RecyclerView.Adapter adapter) {
        return bindAdapterAndLayout(adapter, mRecycler.getLayoutManager() == null ? new SLinearLayoutManager(mContext) : null);
    }

    /**
     * Sets adapter and layout manager
     *
     * @param adapter the adapter
     * @param columns the # of columns
     * @return SRV
     */
    public SwipeRecyclerView setAdapter(RecyclerView.Adapter adapter, int columns) {
        return bindAdapterAndLayout(adapter,
                columns == 1 ? new SLinearLayoutManager(mContext) : new SGridLayoutManager(mContext, columns));
    }

    private SwipeRecyclerView bindAdapterAndLayout(final RecyclerView.Adapter adapter, @Nullable final LinearLayoutManager layoutManager) {
        if (layoutManager != null) mRecycler.setLayoutManager(layoutManager);
        mRecycler.setAdapter(adapter);
        if (adapter instanceof AnimationAdapter) ((AnimationAdapter) adapter).bindSRV(this);
        return this;
    }

    public SwipeRecyclerView disableScrolling() {
        return setScrollingEnabled(false);
    }

    public SwipeRecyclerView enableScrolling() {
        return setScrollingEnabled(true);
    }

    public SwipeRecyclerView setScrollingEnabled(boolean flag) {
        if (!(mRecycler.getLayoutManager() instanceof ILayoutManager))
            CLog.e("Recycler Layout Manager does not have custom ILayoutManager toggle");
        else
            ((ILayoutManager) mRecycler.getLayoutManager()).setScrollEnabled(flag);
        return this;
    }

    public SwipeRecyclerView scrollToPosition(int x, int y) {
        mRecycler.scrollTo(x, y);
        return this;
    }

    public SwipeRecyclerView smoothScrollToPosition(int position) {
        mRecycler.smoothScrollToPosition(position);
        return this;
    }

    /**
     * If ILayoutManager is used, tries to set the duration in the Layout Manager for the next scroll
     *
     * @param position to scroll to
     * @param duration for the entire scroll
     * @return this
     */
    public SwipeRecyclerView smoothScrollToPosition(int position, int duration) {
        if (getLayoutManager() instanceof ILayoutManager)
            ((ILayoutManager) getLayoutManager()).setSmoothScrollDuration(duration);
        return smoothScrollToPosition(position);
    }

    public SwipeRecyclerView smoothScrollBy(int dx, int dy) {
        return smoothScrollBy(dx, dy, null);
    }

    public SwipeRecyclerView smoothScrollBy(int dx, int dy, Interpolator interpolator) {
        mRecycler.smoothScrollBy(dx, dy, interpolator);
        return this;
    }

    public SwipeRecyclerView stopScroll() {
        mRecycler.stopScroll();
        return this;
    }

    /**
     * Sets on refresh listener.
     *
     * @param listener the listener
     * @return the on refresh listener
     */
    public SwipeRecyclerView setOnRefreshListener(@NonNull ISwipeRecycler.OnRefreshListener listener) {
        mRefreshListener = listener;
        enableRefresh();
        return this;
    }

    /**
     * Define refresh success and failure behaviour
     * Setting as null will produce the default behaviour of
     * hiding the refresh spinner when completed
     *
     * @param callback onSuccess and onFailure
     * @return this on refresh status
     */
    public SwipeRecyclerView setOnRefreshStatus(@Nullable ISwipeRecycler.OnRefreshStatus callback) {
        if (callback == null) mRefreshStatus = getDefaultRefreshStatus();
        else mRefreshStatus = callback;
        return this;
    }

    /**
     * Refresh swipe recycler view, calling the listener if it exists
     *
     * @return the swipe recycler view
     */
    public SwipeRecyclerView refresh() {
        mSwipe.setRefreshing(true);
        onRefresh();
        return this;
    }

    /**
     * Variant of refresh where the view is updated without animations and the refresh indicator
     */
    public SwipeRecyclerView refreshSilently() {
        mSilentRefreshListener.onSilentRefresh();
        return this;
    }

    public interface SilentRefreshListener {
        void onSilentRefresh();
    }

    public SilentRefreshListener getDefaultSilentRefreshListener() {
        return new SilentRefreshListener() {
            @Override
            public void onSilentRefresh() {
                onRefresh();
            }
        };
    }

    /**
     * Variant of refresh where the view is updated without animations and the refresh indicator
     */
    public SwipeRecyclerView setSilentRefreshListener(@Nullable SilentRefreshListener listener) {
        if (listener != null) mSilentRefreshListener = listener;
        else mSilentRefreshListener = getDefaultSilentRefreshListener();
        return this;
    }

    @Override
    public boolean shouldConsumeTouch(MotionEvent ev) {
        return !mRecycler.canScrollVertically(-1);
    }

    @Override
    public final void onRefresh() {
        if (mRefreshListener != null) mRefreshListener.onRefresh(mRefreshStatus);
        else CLog.w("Refresh Listener is null");
    }

    /**
     * Gets recycler view.
     *
     * @return recycler view
     */
    public RecyclerView getRecyclerView() {
        return mRecycler;
    }

    /**
     * Gets swipe refresh layout.
     *
     * @return the swipe refresh layout
     */
    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return mSwipe;
    }

    public LinearLayoutManager getLayoutManager() {
        return (LinearLayoutManager) mRecycler.getLayoutManager();
    }

    public boolean isFirstItemCompletelyVisible() {
        return getLayoutManager().findFirstCompletelyVisibleItemPosition() == 0;
    }
}
