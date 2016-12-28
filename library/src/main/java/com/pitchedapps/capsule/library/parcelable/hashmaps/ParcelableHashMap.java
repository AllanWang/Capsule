package com.pitchedapps.capsule.library.parcelable.hashmaps;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

/**
 * Created by Allan Wang on 2016-12-28.
 * <p>
 * Wrapper for sending hashmaps through parcels
 * Relies on getting key value classes through strings
 *
 * @see com.pitchedapps.capsule.library.utils.ParcelUtils
 */

public abstract class ParcelableHashMap<K, V> implements Parcelable {
    private HashMap<K, V> mMap;

    public ParcelableHashMap(HashMap<K, V> map) {
        mMap = map;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mMap.size());
        for (HashMap.Entry<K, V> entry : this.mMap.entrySet()) {
            writeKeyToParcel(dest, entry.getKey(), flags);
            writeValueToParcel(dest, entry.getValue(), flags);
        }
    }

    protected abstract void writeKeyToParcel(Parcel dest, K key, int flags);

    protected abstract void writeValueToParcel(Parcel dest, V value, int flags);

    public HashMap<K, V> getMap() {
        return mMap;
    }

    protected ParcelableHashMap(Parcel in) {
        int mapSize = in.readInt();
        this.mMap = new HashMap<>(mapSize);
        for (int i = 0; i < mapSize; i++) {
            this.mMap.put(readKeyFromParcel(in), readValueFromParcel(in));
        }
    }

    protected abstract K readKeyFromParcel(Parcel in);

    protected abstract V readValueFromParcel(Parcel in);

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
