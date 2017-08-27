package ca.allanwang.capsule.library.custom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;

public class CapsuleAppBarLayout extends AppBarLayout {
    
    private boolean isScrollAllowed = true;
    
    public CapsuleAppBarLayout(Context context) {
        super(context);
    }
    
    public CapsuleAppBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    public boolean isScrollAllowed() {
        return isScrollAllowed;
    }
    
    public void setScrollAllowed(boolean scrollAllowed) {
        isScrollAllowed = scrollAllowed;
        setActivated(scrollAllowed);
        setEnabled(scrollAllowed);
        try {
            CoordinatorLayout.LayoutParams params =
                    (CoordinatorLayout.LayoutParams) getLayoutParams();
            AppBarLayout.Behavior behavior = (Behavior) params.getBehavior();
            if (behavior != null) {
                behavior.setDragCallback(new Behavior.DragCallback() {
                    @Override
                    public boolean canDrag(@NonNull AppBarLayout appBarLayout) {
                        return scrollAllowed;
                    }
                });
                
            }
            params.setBehavior(behavior);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}