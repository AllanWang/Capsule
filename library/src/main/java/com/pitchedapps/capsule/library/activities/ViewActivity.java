package com.pitchedapps.capsule.library.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.pitchedapps.capsule.library.CapsuleFragment;

/**
 * Created by Allan Wang on 2016-08-21.
 * <p>
 * Handles all views
 */
public abstract class ViewActivity extends AppCompatActivity {

    protected FloatingActionButton cFab;
    protected Toolbar cToolbar;

    public FloatingActionButton getFab() {
        if (cFab == null)
            throw new RuntimeException("Fab not set in CapsuleActivity; use setupFab method");
        return cFab;
    }

    public static void hideFab(Context context) {
        if (context instanceof CapsuleActivity) {
            ((CapsuleActivity) context).getFab().hide();
        } else {
            Log.e("hideFab", "context not instance of CapsuleActivity");
        }
    }

    /**
     * Gets the Layout ID of the view that will be replaced by Fragments with the SupportFragmentManager
     *
     * @return layoutID
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
            throw new RuntimeException("Fragment does not extend CapsuleFragment");
        return (CapsuleFragment) current;
    }

    protected Capsulate capsulate() {
        return new Capsulate();
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
    }
}
