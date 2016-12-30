package com.pitchedapps.capsule.library.parcelable.maps;

import java.util.HashMap;

/**
 * Created by Allan Wang on 2016-12-28.
 * <p>
 * Wrapper for sending hashmaps through parcels
 * Relies on getting key value classes through strings
 *
 * @see com.pitchedapps.capsule.library.utils.ParcelUtils
 */

public abstract class ParcelableHashMap<K, V> extends ParcelableMap<K, V, HashMap<K, V>> {

    public ParcelableHashMap(HashMap<K, V> map) {
        super(map);
    }

    @Override
    protected HashMap<K, V> createMap(int mapSize) {
        return new HashMap<>(mapSize);
    }


    /*
    Creator template; replace ClassHere with your subclass

    public static final Creator<ClassHere> CREATOR = new Creator<ClassHere>() {
        @Override
        public ClassHere createFromParcel(Parcel source) {
            return new ClassHere(source);
        }

        @Override
        public ClassHere[] newArray(int size) {
            return new ClassHere[size];
        }
    };

    */
}
