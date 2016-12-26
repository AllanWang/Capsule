package com.pitchedapps.capsule.library.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.pitchedapps.capsule.library.R;
import com.pitchedapps.capsule.library.interfaces.CDrawerItem;
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
    private static final String DRAWER_POSITION = "drawer_position";
    private int lastDrawerPosition = 0;

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
        capsuleFrameOnCreate(savedInstanceState);
        switchFragment(mDrawerItems.get(getLastDrawerPosition()).getFragment());
    }

    protected int getLastDrawerPosition() {
        Bundle extras = getIntent().getExtras();
        if (extras == null) return 0;
        return extras.getInt(DRAWER_POSITION, 0);
    }

    /**
     * Separate onCreate for frame
     * should be called after capsuleOnCreate and preCapsuleOnCreate
     *
     * @param savedInstanceState
     */
    protected void capsuleFrameOnCreate(Bundle savedInstanceState) {
        capsulate()
                .toolbar(R.id.toolbar)
                .appBarLayout(R.id.appbar)
                .coordinatorLayout(R.id.coordinator)
                .collapsingToolbarLayout(R.id.collapsing_toolbar);
        setupDrawer(savedInstanceState);
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

    private void setupDrawer(Bundle savedInstanceState) {
        DrawerBuilder builder = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(cToolbar)
                .withSavedInstance(savedInstanceState)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // position is index + 1; but since the identifier is changed to reflect index, we'll use that
                        int index = (int) drawerItem.getIdentifier();
                        CDrawerItem item = mDrawerItems.get(index);
//                        if (item.getTitleId() <= 0) return false; //special values //TODO figure out what this is
                        if (lastDrawerPosition == index) //Do not recreate fragment if it's at the same index
                            return false;
                        lastDrawerPosition = index;
                        if (item.getFragment() != null) switchFragment(item.getFragment());
                        return false;
                    }
                });

        AccountHeader header = getAccountHeader();
        if (header != null) {
            header.saveInstanceState(savedInstanceState);
            builder.withAccountHeader(header);
        }

        CDrawerItem[] items = getDrawerItems();
        if (items != null && items.length > 0) {
            for (int i = 0; i < items.length; i++) {
                CDrawerItem item = items[i];
                mDrawerItems.add(item);
                if (item.getTitleId() == -1) {
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
        } else if ((int) cDrawer.getCurrentSelection() != 0) {
            cDrawer.setSelection(0);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void reload() {
        finish();
        overridePendingTransition(0, 0); //No transitions
        Intent intent = getIntent();
        intent.putExtra(DRAWER_POSITION, (int) cDrawer.getCurrentSelection());
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

}
