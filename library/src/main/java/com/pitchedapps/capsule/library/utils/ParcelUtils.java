package com.pitchedapps.capsule.library.utils;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

import com.pitchedapps.capsule.library.parcelable.maps.ParcelableMap;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Allan Wang on 2016-12-28.
 * <p>
 * Helper method allowing parcel building as well as HashMap support
 * Builder style for easier calling
 */

public class ParcelUtils<T extends Fragment> {

    private T mFragment;
    private Bundle args;

    //Generic in case you aren't using a fragment
    public ParcelUtils() {
        args = new Bundle();
    }

    //Allows for creation and return of fragment with the arguments
    public ParcelUtils(T fragment) {
        mFragment = fragment;
        args = new Bundle();
    }

    /*
     * Helper builder methods for some common bundle put operations
     */

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

    public ParcelUtils putParcelableArray(String key, Parcelable[] value) {
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

    /**
     * putBoolean, where it puts true if all objects are nonnull and false if otherwise
     *
     * @param key     key to put boolean
     * @param objects list of objects to check for null
     * @return true if all objects are nonnull
     */
    public boolean putNullStatus(String key, Object... objects) {
        for (Object o : objects) {
            if (o == null) {
                args.putBoolean(key, false);
                return false;
            }
        }
        args.putBoolean(key, true);
        return true;
    }

    public <K, V, M extends Map<K, V>> ParcelUtils putMap(String key, ParcelableMap<K, V, M> map) {
        args.putParcelable(key, map);
        return this;
    }

    /**
     * Get HashMap from parcel (watch out for casting)
     *
     * @param <P>                ParcelableMap subclass
     * @param <K>                Map Key
     * @param <V>                Map Value
     * @param <M>                Map itself
     * @param bundle             bundle to retrieve from
     * @param key                key for the parcel
     * @param parcelableMapClass the parcelable map class
     * @return Map of type M, null if not found
     */
    public static <P extends ParcelableMap<K, V, M>, K, V, M extends Map<K, V>> M getMap(Bundle bundle, String key, Class<P> parcelableMapClass) {
        if (bundle == null || !bundle.containsKey(key)) return null;
        P mapWrapper = bundle.getParcelable(key);
        if (mapWrapper == null) return null;
        return mapWrapper.getMap();
    }

    public Bundle getBundle() {
        return args;
    }

    /**
     * Adds existing bundle into existing fragment
     *
     * @return fragment with args
     */
    public T create() {
        if (mFragment == null)
            throw new IllegalArgumentException("No fragment added, use other constructor");
        mFragment.setArguments(args);
        return mFragment;
    }
}
