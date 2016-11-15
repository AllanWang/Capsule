package com.pitchedapps.capsule.library.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.pitchedapps.capsule.library.R;
import com.pitchedapps.capsule.library.interfaces.CDrawerItem;
import com.pitchedapps.capsule.library.interfaces.CFragmentCore;
import com.pitchedapps.capsule.library.logging.CLog;
import com.pitchedapps.capsule.library.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allan Wang on 2016-10-22.
 * <p>
 * Activity with layout included, plus drawer
 */

public abstract class CapsuleActivityFrame extends CapsuleActivity {

    protected Drawer cDrawer;
    private List<CDrawerItem> mDrawerItems = new ArrayList<>();

    @Override
    protected int getFragmentId() {
        return R.id.main;
    }

    @Override
    protected int getFabId() {
        return R.id.fab;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_core;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        capsulate()
                .toolbar(R.id.toolbar)
                .appBarLayout(R.id.appbar)
                .coordinatorLayout(R.id.coordinator)
                .tabLayout(R.id.tabs);
        setupDrawer();
        switchFragment(mDrawerItems.get(0).getFragment());
    }

    @Override
    protected void switchFragment(Fragment fragment) {
        switchFragment(fragment, R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
    }

    /**
     * Sets up account header
     * will not be added if null
     *
     * @return desired header
     */
    protected abstract
    @Nullable
    AccountHeader getAccountHeader();

    /**
     * Sets up array of drawer items
     *
     * @return array of drawer items
     */
    protected abstract CDrawerItem[] getDrawerItems();

    /**
     * For any extra drawer changes
     *
     * @param builder Drawer Builder with added items
     */
    protected void setupDrawerFurther(DrawerBuilder builder) {
        //empty by default
    }

    protected void selectDrawerItem(int position) {
        if (position > mDrawerItems.size() || position == -1)
            throw new RuntimeException("Drawer item position out of bounds: " + position);
        cDrawer.setSelection(position);
        switchFragment(mDrawerItems.get(position).getFragment());
    }

    protected void selectDrawerItem(CDrawerItem item) {
        selectDrawerItem(mDrawerItems.indexOf(item));
    }

    private void setupDrawer() {
        DrawerBuilder builder = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(cToolbar)

                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // position is index + 1; but since the identifier is changed to reflect index, we'll use that
                        switchFragment(mDrawerItems.get((int) drawerItem.getIdentifier()).getFragment());
                        return false;
                    }
                });

        AccountHeader header = getAccountHeader();
        if (header != null) builder.withAccountHeader(header);

        CDrawerItem[] items = getDrawerItems();
        if (items != null && items.length > 0) {
            for (int i = 0; i < items.length; i++) {
                CDrawerItem item = items[i];
                mDrawerItems.add(item);
                if (item.getFragment() == null) {
                    builder.addDrawerItems(new DividerDrawerItem());
                } else if (item.isPrimary()) {
                    PrimaryDrawerItem drawerItem = new PrimaryDrawerItem().withName(item.getTitleId()).withIdentifier(i);
                    if (item.getIcon() != null) {
                        drawerItem.withIcon(ViewUtils.iconDrawable(this, item.getIcon())).withIconTintingEnabled(true);
                    }
                    builder.addDrawerItems(drawerItem);
                } else {
                    SecondaryDrawerItem drawerItem = new SecondaryDrawerItem().withName(item.getTitleId()).withIdentifier(i);
                    builder.addDrawerItems(drawerItem);
                }
            }
        } else {
            CLog.d("Drawer items is an empty list");
        }
        setupDrawerFurther(builder);
        cDrawer = builder.build();
    }

    @Override
    public void onBackPressed() {
        if (cDrawer.isDrawerOpen()) {
            cDrawer.closeDrawer();
        } else if (cDrawer.getCurrentSelection() != 0) {
            cDrawer.setSelection(0);
        } else {
            super.onBackPressed();
        }
    }

}
