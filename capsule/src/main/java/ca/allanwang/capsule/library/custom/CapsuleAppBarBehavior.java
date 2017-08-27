package ca.allanwang.capsule.library.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

public class CapsuleAppBarBehavior extends AppBarLayout.Behavior {
    
    public CapsuleAppBarBehavior() {
        setDragCallback(null);
    }
    
    public CapsuleAppBarBehavior(boolean scrollAllowed) {
        this.scrollAllowed = scrollAllowed;
        setDragCallback(null);
    }
    
    public CapsuleAppBarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDragCallback(null);
    }
    
    private boolean scrollAllowed = true;
    
    @Override
    public void setDragCallback(@Nullable DragCallback ignored) {
        super.setDragCallback(new DragCallback() {
            @Override
            public boolean canDrag(@NonNull AppBarLayout appBarLayout) {
                return scrollAllowed;
            }
        });
    }
    
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout parent, AppBarLayout child,
                                       View directTargetChild, View target, int nestedScrollAxes,
                                       int type) {
        return scrollAllowed && super.onStartNestedScroll(parent, child, directTargetChild, target,
                                                          nestedScrollAxes, type);
    }
    
    public boolean isScrollAllowed() {
        return scrollAllowed;
    }
    
    public void setScrollAllowed(boolean scrollAllowed) {
        this.scrollAllowed = scrollAllowed;
    }
}