package com.pitchedapps.capsule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.pitchedapps.capsule.library.activities.CapsuleActivityFrame;
import com.pitchedapps.capsule.library.changelog.ChangelogDialog;
import com.pitchedapps.capsule.library.interfaces.CCallback;
import com.pitchedapps.capsule.library.interfaces.CDrawerItem;
import com.pitchedapps.capsule.library.item.DrawerItem;

/**
 * Created by Allan Wang on 2016-08-21.
 */
public class MainActivity extends CapsuleActivityFrame {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        cCoordinatorLayout.setScrollAllowed(false);
//        addCollapsingToolbarView(new NumberMorphingView(this));
        addCollapsingToolbarView(R.layout.toolbar_view);
//        new CustomizeToolbar().setHeight(70);
        new CustomizeToolbar().hideTitleOnExpand().setHeight(200);

        onVersionUpdate(BuildConfig.VERSION_CODE, new CCallback() {
            @Override
            public void onResult() {
                ChangelogDialog.show(MainActivity.this, R.xml.changelog, null);
            }
        });
    }

    @Override
    protected void switchFragment(Fragment fragment) {
        if (fragment instanceof SampleSwipeRecyclerFragment) expandAppBar();
        else collapseAppBar();
        super.switchFragment(fragment);
    }

    /**
     * @return desired header
     * Sets up account header
     * will not be added if null
     */
    @Nullable
    @Override
    protected AccountHeaderBuilder getAccountHeaderBuilder() {
        return new AccountHeaderBuilder().withActivity(this)
                .withHeaderBackground(R.color.colorPrimary)
                .withSelectionFirstLine(s(R.string.app_name))
                .withSelectionSecondLine(BuildConfig.VERSION_NAME)
                .withProfileImagesClickable(false)
                .withResetDrawerOnProfileListClick(false)
                .addProfiles(
                        new ProfileDrawerItem().withIcon(ContextCompat.getDrawable(this, R.drawable.ctf))
                )
                .withSelectionListEnabled(false)
                .withSelectionListEnabledForSingleProfile(false);
    }

    /**
     * Sets up array of drawer items
     *
     * @return array of drawer items
     */
    @Override
    protected CDrawerItem[] getDrawerItems() {
        return new CDrawerItem[]{
                new DrawerItem(R.string.home, GoogleMaterial.Icon.gmd_dashboard, true) {
                    @Nullable
                    @Override
                    public Fragment getFragment() {
                        return new FragmentSample();
                    }
                },
                new DrawerItem(R.string.viewpager, GoogleMaterial.Icon.gmd_view_column, true) {
                    @Nullable
                    @Override
                    public Fragment getFragment() {
                        return new ViewPagerFragmentSample();
                    }
                },
                new DrawerItem(R.string.refresh_recycler, GoogleMaterial.Icon.gmd_refresh, true) {
                    @Nullable
                    @Override
                    public Fragment getFragment() {
                        return new SampleSwipeRecyclerFragment();
                    }
                },
                new DrawerItem(R.string.basic_with_fab, GoogleMaterial.Icon.gmd_domain, true) {
                    @Nullable
                    @Override
                    public Fragment getFragment() {
                        return new FragmentSample();
                    }
                },
                new DrawerItem(R.string.basic_no_fab, GoogleMaterial.Icon.gmd_extension, true) {
                    @Nullable
                    @Override
                    public Fragment getFragment() {
                        return new FragmentSampleNoFab();
                    }
                },
                new DrawerItem(R.string.ripple, GoogleMaterial.Icon.gmd_blur_circular, true) {
                    @Nullable
                    @Override
                    public Fragment getFragment() {
                        return new RippleFragment();
                    }
                }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_changelog) {
            ChangelogDialog.show(this, R.xml.changelog, null);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public int switchTheme() {
        if (new SamplePrefs(this).switchTheme()) {
            setTheme(R.style.AppTheme);
            return R.style.AppTheme;
        } else {
            setTheme(R.style.AppTheme_Dark);
            return R.style.AppTheme_Dark;
        }
    }
}
