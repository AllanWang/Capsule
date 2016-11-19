package com.pitchedapps.capsule;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pitchedapps.capsule.library.event.CFabEvent;
import com.pitchedapps.capsule.library.fragments.CapsuleFragment;
import com.pitchedapps.capsule.library.utils.ColourUtils;
import com.pitchedapps.capsule.library.utils.ViewUtils;
import com.pitchedapps.capsule.library.views.RippleCanvas;

/**
 * Created by Allan Wang on 2016-11-17.
 */

public class RippleFragment extends CapsuleFragment {

    private RippleCanvas mRipple;

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ripple_sample, container, false);
        mRipple = (RippleCanvas) v.findViewById(R.id.ripple_bg);
        ViewUtils.setOnClickPositionListener(mRipple, new ViewUtils.OnClickPositionListener() {
            @Override
            public void onClick(View view, float x, float y) {
                mRipple.ripple(ColourUtils.randomLightColor(), x, y);
            }
        });
        return v;
    }

    @Nullable
    @Override
    protected CFabEvent updateFab() {
        return new CFabEvent();
    }

    @Override
    public int getTitleId() {
        return R.string.ripple;
    }
}
