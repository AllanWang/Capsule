package ca.allanwang.capsule.library.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;
import java.util.List;

import ca.allanwang.capsule.library.R;
import ca.allanwang.capsule.library.interfaces.CDrawerItem;
import ca.allanwang.capsule.library.logging.CLog;
import ca.allanwang.capsule.library.utils.ViewUtils;

/**
 * Created by Allan Wang on 2016-10-22.
 * <p>
 * Activity with layout included, plus drawer
 */

public abstract class CapsuleActivityFrame extends CapsuleActivity {

    private static final String DRAWER_POSITION = "drawer_position";
    protected Drawer cDrawer;
    private List<CDrawerItem> mDrawerItems = new ArrayList<>();
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
        return R.layout.capsule_activity_core;
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
     * @param savedInstanceState the saved instance state
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
    public void switchFragment(Fragment fragment) {
        switchFragment(fragment, R.anim.capsule_enter, R.anim.capsule_exit, R.anim.capsule_pop_enter, R.anim.capsule_pop_exit);
    }

    @Override
    public void addFragment(Fragment fragment) {
        addFragment(fragment, R.anim.capsule_enter, R.anim.capsule_exit, R.anim.capsule_pop_enter, R.anim.capsule_pop_exit);
    }

    /**
     * Sets up account header
     * will not be added if null
     *
     * @return desired header
     */
    protected abstract
    @Nullable
    AccountHeaderBuilder getAccountHeaderBuilder();

    /**
     * Optional helper to further setup account header details
     *
     * @param changer protected helper class
     */
    protected void setupAccountHeaderFurther(@NonNull AccountHeaderChanger changer) {

    }

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

    protected void selectDrawerItemFromId(@StringRes int id) {
        int index = getDrawerPositionFromId(id);
        if (index == -1) CLog.e("Invalid drawer item id selection %s", s(this, id));
        else selectDrawerItem(index);
    }

    protected int getDrawerPositionFromId(@StringRes int id) {
        for (int i = 0; i < mDrawerItems.size(); i++) {
            int current = mDrawerItems.get(i).getTitleId();
            if (current == id) return i;
        }
        return -1;
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

        AccountHeaderBuilder headerBuilder = getAccountHeaderBuilder();
        if (headerBuilder != null) {
            headerBuilder.withSavedInstance(savedInstanceState);
            AccountHeader header = headerBuilder.build();
            setupAccountHeaderFurther(new AccountHeaderChanger(header));
            builder.withAccountHeader(header);
        }

        CDrawerItem[] items = getDrawerItems();
        if (items != null && items.length > 0) {
            for (int i = 0; i < items.length; i++) {
                CDrawerItem item = items[i];
                mDrawerItems.add(item);
                if (item.getTitleId() == -1)
                    builder.addDrawerItems(new DividerDrawerItem());
                else if (item.isPrimary()) {
                    PrimaryDrawerItem drawerItem = new PrimaryDrawerItem().withName(item.getTitleId()).withIdentifier(i);
                    if (item.getIcon() != null)
                        drawerItem.withIcon(ViewUtils.iconDrawable(this, item.getIcon())).withIconTintingEnabled(true);
                    builder.addDrawerItems(drawerItem);
                } else {
                    SecondaryDrawerItem drawerItem = new SecondaryDrawerItem().withName(item.getTitleId()).withIdentifier(i);
                    builder.addDrawerItems(drawerItem);
                }
            }
        } else
            CLog.d("Drawer items is an empty list");
        setupDrawerFurther(builder);
        cDrawer = builder.build();
        cDrawer.getAdapter().withPositionBasedStateManagement(false); //Fix for programmatically selecting drawer items; #1666
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            return; //fragment was popped
        }
        if (cDrawer != null) {
            if (cDrawer.isDrawerOpen())
                cDrawer.closeDrawer();
            else if ((int) cDrawer.getCurrentSelection() != 0)
                cDrawer.setSelection(0);
            else
                super.onBackPressed();
            return;
        }
        super.onBackPressed();
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (cDrawer != null) outState = cDrawer.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    protected class AccountHeaderChanger {
        private AccountHeader header;

        private AccountHeaderChanger(@NonNull AccountHeader header) {
            this.header = header;
        }

        public TextView getHeaderName() {
            return ((TextView) header.getView().findViewById(R.id.material_drawer_account_header_name));
        }

        public TextView getHeaderEmail() {
            return ((TextView) header.getView().findViewById(R.id.material_drawer_account_header_email));
        }

        public AccountHeaderChanger setTitle(@StringRes int id) {
            return setTitle(s(CapsuleActivityFrame.this, id));
        }

        public AccountHeaderChanger setTitle(@NonNull String text) {
            getHeaderName().setText(text);
            return this;
        }

        protected AccountHeaderChanger setSubtitle(@StringRes int id) {
            return setSubtitle(s(CapsuleActivityFrame.this, id));
        }

        protected AccountHeaderChanger setSubtitle(@NonNull String text) {
            getHeaderEmail().setText(text);
            return this;
        }
    }

}
