package com.pitchedapps.capsule.library.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.pitchedapps.capsule.library.R;
import com.pitchedapps.capsule.library.custom.CapsuleCoordinatorLayout;
import com.pitchedapps.capsule.library.fragments.CapsuleFragment;
import com.pitchedapps.capsule.library.logging.CLog;

/**
 * Created by Allan Wang on 2016-08-21.
 * <p>
 * Handles all views
 */
public abstract class ViewActivity extends BaseActivity {

    protected FloatingActionButton cFab;
    protected Toolbar cToolbar;
    protected AppBarLayout cAppBarLayout;
    protected CapsuleCoordinatorLayout cCoordinatorLayout;

    public FloatingActionButton getFab() {
        if (cFab == null) throw new RuntimeException(s(R.string.fab_not_set));
        return cFab;
    }

    public static void hideFab(Context context) {
        if (context instanceof CapsuleActivity) {
            ((CapsuleActivity) context).getFab().hide();
        } else {
            CLog.d(s(context, R.string.capsule_activity_context_error));
        }
    }

    /**
     * Gets the View ID of the view that will be replaced by Fragments with the SupportFragmentManager
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
     * @return layoutID
     */
    protected abstract
    @LayoutRes
    int getContentViewId();

    /**
     * Initializes the necessary views; always call super
     *
     * @param savedInstanceState
     */
    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        setupFab();
    }

    private void setupFab() {
        cFab = (FloatingActionButton) findViewById(getFabId());
        cFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCurrentBaseFragment().onFabClick(view);
            }
        });
    }

    private CapsuleFragment getCurrentBaseFragment() {
        Fragment current = getSupportFragmentManager().findFragmentById(getFragmentId());
        if (!(current instanceof CapsuleFragment))
            throw new RuntimeException(s(R.string.base_fragment_context_error));
        return (CapsuleFragment) current;
    }

    protected Capsulate capsulate() {
        return new Capsulate();
    }

    private void ceAppBar(boolean b) {
        if (cAppBarLayout == null)
            throw new RuntimeException(String.format(s(R.string.generic_not_set), "cAppBarLayout"));
        if (cCoordinatorLayout == null)
            throw new RuntimeException(String.format(s(R.string.generic_not_set), "cCoordinatorLayout"));
        cAppBarLayout.setExpanded(b);
        cCoordinatorLayout.setScrollAllowed(true);
    }

    public void collapseAppBar() {
        ceAppBar(false);
    }

    public void expandAppBar() {
        ceAppBar(true);
    }

    /**
     * Capsulate
     * <p>
     * Helps with initializing and managing other types of views
     */

    protected class Capsulate {

        public Capsulate toolbar(@IdRes int id) {
            cToolbar = (Toolbar) findViewById(id);
            setSupportActionBar(cToolbar);
            return this;
        }

        public Capsulate appBarLayout(@IdRes int id) {
            cAppBarLayout = (AppBarLayout) findViewById(id);
            return this;
        }

        public Capsulate cCoordinatorLayout(@IdRes int id) {
            cCoordinatorLayout = (CapsuleCoordinatorLayout) findViewById(id);
            return this;
        }
    }
}
