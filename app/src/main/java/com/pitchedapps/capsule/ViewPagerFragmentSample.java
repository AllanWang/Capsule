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
                new PageItem(new FragmentSampleNoIcon(), R.string.account, GoogleMaterial.Icon.gmd_satellite, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar("tes");
                    }
                }),
                new PageItem(new FragmentSampleNoIcon(), R.string.settings, GoogleMaterial.Icon.gmd_access_alarm, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setFabIcon(GoogleMaterial.Icon.gmd_image);
                    }
                })
        });
    }
}
