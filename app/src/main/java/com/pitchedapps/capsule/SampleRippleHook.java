package com.pitchedapps.capsule;

import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;

import com.pitchedapps.capsule.library.utils.ColourUtils;
import com.pitchedapps.capsule.library.views.RippleCanvas;

/**
 * Created by Allan Wang on 30/12/2016.
 */

class SampleRippleHook implements View.OnTouchListener {

    private RippleCanvas mRippleCanvas;
    private static final long FIRST_RIPPLE_THRESHOLD = 1000L, NEXT_RIPPLE_THRESHOLD = 200L; //1 second before next ripple, or 200ms after long press
    private boolean rippleSpam = false;
    private long mLastRippleTime = 0;

    SampleRippleHook(@NonNull RippleCanvas rippleCanvas) {
        mRippleCanvas = rippleCanvas;
        mRippleCanvas.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                rippleSpam = false;
                ripple(v, event);
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_MOVE:
                if (rippleSpam && System.currentTimeMillis() - mLastRippleTime > NEXT_RIPPLE_THRESHOLD) {
                    ripple(v, event);
                } else if (System.currentTimeMillis() - mLastRippleTime > FIRST_RIPPLE_THRESHOLD) {
                    rippleSpam = true;
                    ripple(v, event);
                }

                return true;
        }
        return false;
    }

    private void ripple(View v, MotionEvent event) {
        mLastRippleTime = System.currentTimeMillis();
        mRippleCanvas.ripple(ColourUtils.randomLightColor(), event.getX(), event.getY());
    }
}
