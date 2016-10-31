package com.pitchedapps.capsule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;

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
        cCoordinatorLayout.setScrollAllowed(false);
        onVersionUpdate(BuildConfig.VERSION_CODE, new CCallback() {
            @Override
            public void onResult() {
                ChangelogDialog.show(MainActivity.this, R.xml.changelog);
            }
        });
    }

    /**
     * @return desired header
     * Sets up account header
     * will not be added if null
     */
    @Nullable
    @Override
    protected AccountHeader getAccountHeader() {
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
                .withSelectionListEnabledForSingleProfile(false)
                .build();
    }

    /**
     * Sets up array of drawer items
     *
     * @return array of drawer items
     */
    @Override
    protected CDrawerItem[] getDrawerItems() {
        return new CDrawerItem[]{
                new DrawerItem(new FragmentSample(), R.string.home, GoogleMaterial.Icon.gmd_dashboard, true),
                new DrawerItem(new ViewPagerFragmentSample(), R.string.room, GoogleMaterial.Icon.gmd_weekend, true),
                new DrawerItem(new FragmentSample(), R.string.account, GoogleMaterial.Icon.gmd_person, true),
                new DrawerItem(new FragmentSampleNoIcon(), R.string.report, GoogleMaterial.Icon.gmd_error, true),
                new DrawerItem(new FragmentSampleNoFab(), R.string.settings, GoogleMaterial.Icon.gmd_settings, true)
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
            ChangelogDialog.show(this, R.xml.changelog);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
