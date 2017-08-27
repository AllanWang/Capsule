package ca.allanwang.capsule.library.custom;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;

@CoordinatorLayout.DefaultBehavior(CapsuleAppBarBehavior.class)
public class CapsuleAppBarLayout extends AppBarLayout {
    
    private boolean isExpanded = true;
    private boolean isScrollAllowed = true;
    
    public CapsuleAppBarLayout(Context context) {
        super(context);
    }
    
    public CapsuleAppBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    public boolean isExpanded() {
        return isExpanded;
    }
    
    @Override
    public void setExpanded(boolean expanded, boolean animate) {
        isExpanded = expanded;
        super.setExpanded(expanded, animate);
    }
    
    public boolean isScrollAllowed() {
        return isScrollAllowed;
    }
    
    public void setScrollAllowed(boolean scrollAllowed) {
        isScrollAllowed = scrollAllowed;
        // setActivated(scrollAllowed);
        // setEnabled(scrollAllowed);
        try {
            CoordinatorLayout.LayoutParams params =
                    (CoordinatorLayout.LayoutParams) getLayoutParams();
            params.setBehavior(new CapsuleAppBarBehavior(scrollAllowed));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}