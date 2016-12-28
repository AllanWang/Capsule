package com.pitchedapps.capsule.library.utils;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

import com.pitchedapps.capsule.library.item.ParcelableMapWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allan Wang on 2016-12-28.
 * <p>
 * Helper method allowing parcel building as well as maps support
 */

public class ParcelUtils {

    private Fragment mFragment;
    private Bundle args = new Bundle();

    //Generic in case you aren't using a fragment
    public ParcelUtils() {

    }

    public ParcelUtils(Fragment fragment) {
        mFragment = fragment;
    }

    public Bundle getBundle() {
        return args;
    }

    public ParcelUtils putBoolean(String key, boolean value) {
        args.putBoolean(key, value);
        return this;
    }

    public ParcelUtils putString(String key, String value) {
        args.putString(key, value);
        return this;
    }

    public ParcelUtils putInt(String key, int value) {
        args.putInt(key, value);
        return this;
    }

    public ParcelUtils putParcelable(String key, Parcelable value) {
        args.putParcelable(key, value);
        return this;
    }

    public ParcelUtils putBoolean(String key, Parcelable[] value) {
        args.putParcelableArray(key, value);
        return this;
    }

    public ParcelUtils putParcelableArrayList(String key, ArrayList<? extends Parcelable> value) {
        args.putParcelableArrayList(key, value);
        return this;
    }

    public ParcelUtils putBundle(Bundle bundle) {
        args.putAll(bundle);
        return this;
    }

    public <K extends Parcelable, V extends Parcelable> ParcelUtils putHashMap(String key, HashMap<K, V> value, Class<K> keyClass, Class<V> valueClass) {
        args.putParcelable(key, new ParcelableMapWrapper<K, V>(value, keyClass, valueClass));
        return this;
    }

    public <K extends Parcelable, V extends Parcelable> HashMap<K, V> getHashMap(Bundle bundle, String key) {
        if (bundle == null) return null;
        if (!bundle.containsKey(key)) return null;
        ParcelableMapWrapper<K, V> mapWrapper = bundle.getParcelable(key);
        if (mapWrapper == null) return null;
        return mapWrapper.getMap();
    }

    public Fragment create() {
        if (mFragment == null)
            throw new IllegalArgumentException("No fragment added, use other constructor");
        mFragment.setArguments(args);
        return mFragment;
    }
}
