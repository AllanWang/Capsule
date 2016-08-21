package com.pitchedapps.capsule;

import android.os.Bundle;

import com.pitchedapps.capsule.library.CapsuleActivity;

/**
 * Created by Allan Wang on 2016-08-21.
 */
public class MainActivity extends CapsuleActivity {
    @Override
    protected int getFragmentId() {
        return R.id.main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        capsulate().fab(R.id.fab).toolbar(R.id.toolbar);
    }
}
