package ca.allanwang.capsule.sample.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.allanwang.capsule.library.event.CFabEvent;
import ca.allanwang.capsule.library.fragments.CapsuleFragment;
import ca.allanwang.capsule.library.views.RippleCanvas;
import ca.allanwang.capsule.sample.R;
import ca.allanwang.capsule.sample.helpers.SampleRippleHook;

/**
 * Created by Allan Wang on 2016-11-17.
 */

public class RippleFragment extends CapsuleFragment {

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ripple_sample, container, false);
        RippleCanvas mRipple = (RippleCanvas) v.findViewById(R.id.ripple_bg);
        new SampleRippleHook(mRipple);
//        ViewUtils.setOnClickPositionListener(mRipple, new ViewUtils.OnClickPositionListener() {
//            @Override
//            public void onClick(View view, float x, float y) {
//                mRipple.ripple(ColourUtils.randomLightColor(), x, y);
//            }
//        });
        return v;
    }

    @Nullable
    @Override
    protected CFabEvent updateFab() {
        return new CFabEvent(false);
    }

    @Override
    public int getTitleId() {
        return R.string.ripple;
    }
}
