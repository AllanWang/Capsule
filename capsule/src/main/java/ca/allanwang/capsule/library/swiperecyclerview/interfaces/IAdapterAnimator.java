package ca.allanwang.capsule.library.swiperecyclerview.interfaces;

import android.animation.Animator;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Interpolator;

/**
 * Created by Allan Wang on 2017-03-09.
 */

public interface IAdapterAnimator {

    boolean isFirstOnly();

    int getDuration();

    @NonNull
    Interpolator getInterpolator();

    @NonNull
    Animator[] getAnimators(View view, boolean isEmpty);

}
