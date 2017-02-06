package ca.allanwang.capsule.library.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Allan Wang on 2016-11-17.
 * <p>
 * Canvas drawn ripples that keep the previous color
 * Extends to view dimensions
 * Supports multiple ripples from varying locations
 */
public class RippleCanvas extends View {

    public static final float MIDDLE = -1.0f;
    private Paint mPaint;
    private int baseColor = Color.TRANSPARENT;
    private List<Ripple> mRipples = new ArrayList<>();

    public RippleCanvas(Context context) {
        super(context);
        init();
    }

    public RippleCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RippleCanvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(baseColor);
        Iterator<Ripple> itr = mRipples.iterator();
        while (itr.hasNext()) {
            Ripple r = itr.next();
            mPaint.setColor(r.color);
            canvas.drawCircle(r.x, r.y, r.radius, mPaint);
            if (r.radius == r.maxRadius) {
                itr.remove();
                baseColor = r.color;
            }
        }
    }

    public void ripple(int color, float x, float y, int duration) {
        float w = getWidth();
        float h = getHeight();
        if (x == MIDDLE) x = w / 2;
        else if (x > w) x = 0f;
        if (y == MIDDLE) y = h / 2;
        else if (y > h) y = 0f;
        float maxRadius = (float) Math.hypot(Math.max(x, w - x), Math.max(y, h - y));
        final Ripple ripple = new Ripple(color, x, y, 0f, maxRadius);
        mRipples.add(ripple);
        ValueAnimator animator = ValueAnimator.ofFloat(0, maxRadius);
        animator.setDuration(duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ripple.setRadius((float) animation.getAnimatedValue());
                invalidate();
            }
        });
        animator.start();
    }

    public void ripple(int color, float x, float y) {
        ripple(color, x, y, 1000);
    }

    public void ripple(int color) {
        ripple(color, 0f, 0f);
    }

    public void set(int color) {
        baseColor = color;
        mRipples.clear();
        invalidate();
    }

    private class Ripple {
        private final int color;
        private final float x, y, maxRadius;
        private float radius;

        private Ripple(int c, float x, float y, float r, float max) {
            color = c;
            this.x = x;
            this.y = y;
            radius = r;
            maxRadius = max;
        }

        void setRadius(float r) {
            radius = r;
        }
    }
}
