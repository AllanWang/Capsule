package com.pitchedapps.capsule;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.pitchedapps.capsule.library.fragments.CapsuleFragment;
import com.pitchedapps.capsule.library.logging.CLog;

/**
 * Created by Allan Wang on 2016-08-21.
 */
public class FragmentSample extends CapsuleFragment {
    @Override
    public void onFabClick(View v) {
        snackbar("Test", Snackbar.LENGTH_LONG);
    }

    @Override
    public int getTitleId() {
        return R.string.sample_fragment;
    }

    @Override
    public int getFabIcon() {
        return android.R.drawable.ic_dialog_email;
    }

    @Override
    public boolean hasFab() {
        return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_sample, container, false);
        ((Button) v.findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar("TEST", Snackbar.LENGTH_LONG);
            }
        });
        return v;
    }
}
