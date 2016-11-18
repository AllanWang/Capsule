package com.pitchedapps.capsule.library.views;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Allan Wang on 2016-11-17.
 * Canvas drawn ripples that keep the previous color
 * Extends to view dimensions
 */

public class RippleCanvas extends View {

    private Paint mPaint;
    private int baseColor = Color.TRANSPARENT, newColor;
    private float x, y, radius, maxRadius;
    public static final float MIDDLE = -1.0f;

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

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(baseColor);
        canvas.drawCircle(x, y, radius, mPaint);
        if (radius == maxRadius) {
            baseColor = newColor;
        }
    }

    public void ripple(int color, float x, float y, int duration) {
        newColor = color;
        mPaint.setColor(newColor);
        int w = getWidth();
        int h = getHeight();
        if (x == MIDDLE) x = w / 2;
        else if (x > w) x = 0f;
        if (y == MIDDLE) y = h / 2;
        else if (y > h) y = 0f;
        this.x = x;
        this.y = y;
        maxRadius = (float) Math.hypot(Math.max(x, w - x), Math.max(y, h - y));
        invalidate();
        ObjectAnimator anim = ObjectAnimator.ofFloat(this, "radius", 0, maxRadius);
        anim.setDuration(duration);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                invalidate();
            }
        });
        anim.start();
    }

    public void ripple(int color, float x, float y) {
        ripple(color, x, y, 1000);
    }

    public void ripple(int color) {
        ripple(color, 0f, 0f);
    }

    public void set(int color) {
        baseColor = color;
        radius = 0;
        invalidate();
    }
}
