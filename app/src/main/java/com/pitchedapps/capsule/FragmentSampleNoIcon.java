package com.pitchedapps.capsule;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.typeface.IIcon;
import com.pitchedapps.capsule.library.fragments.CapsuleFragment;
import com.pitchedapps.capsule.library.utils.ViewUtils;

/**
 * Created by Allan Wang on 2016-08-21.
 */
public class FragmentSampleNoIcon extends CapsuleFragment {
    @Override
    public void onFabClick(View v) {
        snackbar("Test", Snackbar.LENGTH_LONG);
    }

    @Override
    public int getTitleId() {
        return R.string.sample_fragment;
    }

    @Override
    public IIcon getFabIcon() {
        return null;
    }

    @Override
    public Boolean hasFab() {
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_sample, container, false);
        FrameLayout frame = (FrameLayout) v.findViewById(R.id.fragment_main);
        frame.setBackgroundColor(ViewUtils.randomLightColor());
//        AnimUtils.rootCircularReview(v);
        ((Button) v.findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar("TEST", Snackbar.LENGTH_LONG);
            }
        });
        return v;
    }

}
