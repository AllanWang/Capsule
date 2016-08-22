package com.pitchedapps.capsule;

import android.os.Bundle;

import com.pitchedapps.capsule.library.activities.CapsuleActivity;

/**
 * Created by Allan Wang on 2016-08-21.
 */
public class MainActivity extends CapsuleActivity {
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
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        enableCLog();
        super.onCreate(savedInstanceState);
        capsulate().toolbar(R.id.toolbar);
        switchFragment(new FragmentSample());
    }
}
