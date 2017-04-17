package ca.allanwang.capsule.library.swiperecyclerview.animators;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import ca.allanwang.capsule.library.swiperecyclerview.interfaces.IItemAnimatorExtension;
import ca.allanwang.capsule.library.swiperecyclerview.wasabeef.animators.BaseItemAnimator;


/**
 * Created by Allan Wang on 2017-03-09.
 * Sliding Animator; enter up, exit right, with fading for both
 */

public class SlidingAnimator extends BaseItemAnimator implements IItemAnimatorExtension {

    public static final float SLOW = 1.5f, NORMAL = 1.0f, FAST = 0.5f;
    private float multiplier;

    //animate from base; set true if holders are relatively big
    private boolean fromBase = false, allowFromBase = false;

    public SlidingAnimator() {
        mInterpolator = new DecelerateInterpolator();
        setTimings(NORMAL);
    }

    public SlidingAnimator(Interpolator interpolator) {
        mInterpolator = interpolator;
        setTimings(NORMAL);
    }

    public void setTimings(float multiplier) {
        this.multiplier = multiplier;
        setAddDuration((long) (300 * multiplier));
        setRemoveDuration((long) (300 * multiplier));
    }

    /**
     * Allow animation from base of adapter if it is the last child
     *
     * @param fromBase true to allow
     * @return this
     */
    public ca.allanwang.capsule.library.swiperecyclerview.animators.SlidingAnimator setFromBase(boolean fromBase) {
        return setFromBase(fromBase, true);
    }

    private ca.allanwang.capsule.library.swiperecyclerview.animators.SlidingAnimator setFromBase(Boolean fromBase, boolean allowFromBase) {
        if ((fromBase == null || this.fromBase == fromBase) && this.allowFromBase == allowFromBase)
            return this;
        this.allowFromBase = allowFromBase;
        if (fromBase != null) this.fromBase = fromBase;
        setAddDuration((long) ((this.fromBase && allowFromBase ? 500 : 300) * multiplier));
        return this;
    }

    @Override
    protected void animateRemoveImpl(final RecyclerView.ViewHolder holder) {
        ViewCompat.animate(holder.itemView)
                .translationX(holder.itemView.getRootView().getWidth())
                .alpha(0)
                .setDuration(getRemoveDuration())
                .setInterpolator(mInterpolator)
                .setListener(new DefaultRemoveVpaListener(holder))
                .setStartDelay(getRemoveDelay(holder))
                .start();
    }

    @Override
    protected long getRemoveDelay(final RecyclerView.ViewHolder holder) {
        return Math.max(holder.getOldPosition() * getRemoveDuration() * 4, 0);
    }

    @Override
    protected long getAddDelay(final RecyclerView.ViewHolder holder) {
        return Math.max(holder.getAdapterPosition() * getAddDuration() / 10, 0);
    }

    /**
     * Set sliding base
     * If there are many holders that fit in the screen, do not slide from the bottom
     *
     * @param holder to animate
     */
    @Override
    protected void preAnimateAddImpl(RecyclerView.ViewHolder holder) {
        if (fromBase && allowFromBase) slideInFromBase(holder);
        else fadeSlideIn(holder);
    }

    private void fadeSlideIn(RecyclerView.ViewHolder holder) {
        ViewCompat.setTranslationY(holder.itemView, holder.itemView.getHeight() * 2);
        ViewCompat.setAlpha(holder.itemView, 0);
    }

    private void slideInFromBase(RecyclerView.ViewHolder holder) {
        long displacement = (long) Math.max(holder.itemView.getHeight(),
                ((View) holder.itemView.getParent()).getHeight() - holder.itemView.getY());
        ViewCompat.setTranslationY(holder.itemView, displacement);
    }

    @Override
    protected void animateAddImpl(final RecyclerView.ViewHolder holder) {
        ViewCompat.animate(holder.itemView)
                .translationY(0)
                .alpha(1)
                .setDuration(getAddDuration())
                .setInterpolator(mInterpolator)
                .setListener(new DefaultAddVpaListener(holder))
                .setStartDelay(getAddDelay(holder))
                .start();
    }

    @Override
    public void triggerAdd(boolean toTop, boolean toBottom, boolean isEmpty) {
        setFromBase(null, toBottom);
    }
}