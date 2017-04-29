package ca.allanwang.capsule.library.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import ca.allanwang.capsule.library.R;
import ca.allanwang.capsule.library.custom.CapsuleCoordinatorLayout;
import ca.allanwang.capsule.library.event.CClickEvent;
import ca.allanwang.capsule.library.event.SnackbarEvent;
import ca.allanwang.capsule.library.interfaces.CCollapseListener;
import ca.allanwang.capsule.library.utils.AnimUtils;
import ca.allanwang.capsule.library.utils.ViewUtils;

/**
 * Created by Allan Wang on 2016-08-21. <p> Handles all views
 */
abstract class ViewActivity extends PermissionActivity {

    protected FloatingActionButton cFab;
    protected Toolbar cToolbar;
    protected AppBarLayout cAppBarLayout;
    protected CapsuleCoordinatorLayout cCoordinatorLayout;
    protected TabLayout cTabs;
    protected CollapsingToolbarLayout cCollapsingToolbarLayout;
    protected boolean cToolbarClick = false;

    @Override
    public FloatingActionButton getFab() {
        if (cFab == null) throw new RuntimeException(s(R.string.capsule_fab_not_set));
        return cFab;
    }

    /**
     * Gets the View ID of the view that will be replaced by Fragments with the
     * SupportFragmentManager
     *
     * @return viewID
     */
    protected abstract
    @IdRes
    int getFragmentId();

    /**
     * Gets the fab ID
     *
     * @return fabID
     */
    protected abstract
    @IdRes
    int getFabId();

    /**
     * Gets your layout ID for the activity
     *
     * @return layoutID content view id
     */
    protected abstract
    @LayoutRes
    int getContentViewId();

    /**
     * Capsule's view creation
     */
    @Override
    @CallSuper
    protected void capsuleOnCreate(Bundle savedInstanceState) {
        super.capsuleOnCreate(savedInstanceState);
        setContentView(getContentViewId());
        setupFab();
    }

    private void setupFab() {
        cFab = (FloatingActionButton) findViewById(getFabId());
    }

    /**
     * Returns fragment in viewgroup
     *
     * @return current fragment in view
     */
    protected Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(getFragmentId());
    }

    /**
     * Method to send various ids to Capsule to get their appropriate views
     *
     * @return inner class holding capsulate methods
     */
    protected Capsulate capsulate() {
        return new Capsulate();
    }

    /**
     * Collapse/Expand AppBar
     *
     * @param expand  true for expand, false for collapse
     * @param animate true to animate, false to do not
     */
    private void ceAppBar(boolean expand, boolean animate) {
        if (cAppBarLayout == null)
            throw new RuntimeException(sf(R.string.capsule_generic_not_set, "cAppBarLayout"));
        if (cCoordinatorLayout == null)
            throw new RuntimeException(sf(R.string.capsule_generic_not_set, "cCoordinatorLayout"));
        cAppBarLayout.setExpanded(expand, animate);
        cCoordinatorLayout.setScrollAllowed(expand);
    }

    public void collapseAppBar(boolean animate) {
        ceAppBar(false, animate);
    }

    public void collapseAppBar() {
        collapseAppBar(true);
    }

    public void expandAppBar(boolean animate) {
        ceAppBar(true, animate);
    }

    public void expandAppBar() {
        expandAppBar(true);
    }

    /**
     * Add view into {@link CollapsingToolbarLayout}
     *
     * @param <T>  the type parameter
     * @param view view to add
     * @return view added
     */
    protected <T extends View> T addCollapsingToolbarView(T view) {
        if (cCollapsingToolbarLayout == null)
            throw new NullPointerException(sf(R.string.capsule_generic_not_set,
                    "CollapsingToolbarLayout"));
        cCollapsingToolbarLayout.setTitleEnabled(true);
        cCollapsingToolbarLayout.addView(view, 0);
        return view;
    }

    /**
     * Inflate layout from id and add to {@link CollapsingToolbarLayout}
     *
     * @param layoutId id of layout
     * @return view created
     */
    protected View addCollapsingToolbarView(@LayoutRes int layoutId) {
        View view = getLayoutInflater().inflate(layoutId, null);
        return addCollapsingToolbarView(view);
    }

    public void showTabs(@NonNull ViewPager viewPager) {
        if (cTabs == null)
            throw new NullPointerException(sf(R.string.capsule_generic_not_set, "TabLayout"));
        cTabs.setupWithViewPager(viewPager);
        AnimUtils.slideEnter(cTabs);
//        cTabs.setVisibility(View.VISIBLE);
    }

    public void hideTabs() {
        if (cTabs == null)
            throw new RuntimeException(sf(R.string.capsule_generic_not_set, "TabLayout"));
        AnimUtils.slideExit(cTabs, v -> cTabs.removeAllTabs());
//        cTabs.setVisibility(View.GONE);
//        cTabs.removeAllTabs();
    }

    /**
     * Set toolbar title
     *
     * @param title String
     */
    protected void setTitle(@Nullable String title) {
        if (title == null) return;
        cToolbar.setTitle(title);
        if (cCollapsingToolbarLayout != null) cCollapsingToolbarLayout.setTitle(title);
    }

    protected void snackbar(SnackbarEvent event) {
        postEvent(event);
    }

    /**
     * Capsulate <p> Helps with initializing and managing other types of views
     */
    protected class Capsulate {

        public Capsulate toolbar(@IdRes int id) {
            cToolbar = (Toolbar) findViewById(id);
            setSupportActionBar(cToolbar);
            cToolbar.setOnClickListener(v -> {
                if (cToolbarClick) EventBus.getDefault().post(new CClickEvent(v));
            });
            return this;
        }

        public Capsulate appBarLayout(@IdRes int id) {
            cAppBarLayout = (AppBarLayout) findViewById(id);
            return this;
        }

        public Capsulate coordinatorLayout(@IdRes int id) {
            cCoordinatorLayout = (CapsuleCoordinatorLayout) findViewById(id);
            return this;
        }

        public Capsulate tabLayout(@IdRes int id) {
            cTabs = (TabLayout) findViewById(id);
            return this;
        }

        public Capsulate collapsingToolbarLayout(@IdRes int id) {
            cCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(id);
            cCollapsingToolbarLayout.setTitleEnabled(false); //Until an inner view is added, show
            // title from toolbar
            return this;
        }
    }

    /**
     * Collection of helper classes for customizing the {@link CollapsingToolbarLayout}
     * Throws error if not set
     */
    protected class CustomizeToolbar {

        public CustomizeToolbar() {
            if (cCollapsingToolbarLayout == null)
                throw new NullPointerException(sf(R.string.capsule_generic_not_set,
                        "CollapsingToolbar"));
        }

        public CustomizeToolbar setHeight(int dp) {
            ViewGroup.LayoutParams params = cCollapsingToolbarLayout.getLayoutParams();
            if (dp > 0) params.height = ViewUtils.dpToPx(dp);
            else params.height = dp;
            cCollapsingToolbarLayout.setLayoutParams(params);
            return this;
        }

        public CustomizeToolbar setTitleColor(@ColorInt int color) {
            cCollapsingToolbarLayout.setCollapsedTitleTextColor(color);
            return this;
        }

        public CustomizeToolbar hideTitleOnExpand() {
            cCollapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
            return this;
        }

        public CustomizeToolbar setScrimColor(@ColorInt int color) {
            cCollapsingToolbarLayout.setContentScrimColor(color);
            return this;
        }

        public CustomizeToolbar setCollapseListener(@NonNull CCollapseListener listener) {
            cAppBarLayout.addOnOffsetChangedListener(listener);
            return this;
        }

        public CustomizeToolbar withClickEvents(boolean enable) {
            cToolbarClick = enable;
            return this;
        }

    }

}
