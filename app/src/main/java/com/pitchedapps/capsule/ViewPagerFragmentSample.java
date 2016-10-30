package com.pitchedapps.capsule;

import android.view.View;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.pitchedapps.capsule.library.fragments.ViewPagerFragment;
import com.pitchedapps.capsule.library.interfaces.CPage;
import com.pitchedapps.capsule.library.item.PageItem;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Allan Wang on 2016-10-30.
 */

public class ViewPagerFragmentSample extends ViewPagerFragment {
    @Override
    protected List<CPage> setPages() {
        return Arrays.asList(new CPage[]{
                new PageItem(new FragmentSample(), R.string.home, GoogleMaterial.Icon.gmd_3d_rotation, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }),
                new PageItem(new FragmentSample(), R.string.settings, GoogleMaterial.Icon.gmd_access_alarm, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
        });
    }
}
