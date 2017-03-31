package ca.allanwang.capsule.library.activities;

import android.os.Bundle;
import android.support.annotation.CallSuper;

/**
 * Created by Allan Wang on 2016-08-19.
 * <p>
 * The activity that holds everything, with a few extra helper methods
 */
public abstract class CapsuleActivity extends FragmentManagerActivity {

    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        capsuleOnCreate(savedInstanceState);
    }



}
