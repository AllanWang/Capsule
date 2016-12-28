package com.pitchedapps.capsule.library.item;

import android.os.Parcel;
import android.os.Parcelable;

import com.pitchedapps.capsule.library.logging.CLog;

import java.util.HashMap;

/**
 * Created by Allan Wang on 2016-12-28.
 * <p>
 * Wrapper for sending hashmaps through parcels
 * Relies on getting key value classes through strings
 * @see com.pitchedapps.capsule.library.utils.ParcelUtils
 */

public class ParcelableHashMapWrapper<K extends Parcelable, V extends Parcelable> implements Parcelable {
    private HashMap<K, V> mMap;
    private transient Class keyClass, valueClass;
    private String keyClassName, valueClassName;

    public ParcelableHashMapWrapper(HashMap<K, V> map, Class<K> keyClass, Class<V> valueClass) {
        this.mMap = map;
        this.keyClass = keyClass;
        this.valueClass = valueClass;
        keyClassName = keyClass.getName();
        valueClassName = valueClass.getName();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.keyClassName);
        dest.writeString(this.valueClassName);
        dest.writeInt(this.mMap.size());
        for (HashMap.Entry<K, V> entry : this.mMap.entrySet()) {
            dest.writeParcelable(entry.getKey(), flags);
            dest.writeParcelable(entry.getValue(), flags);
        }
    }

    public HashMap<K, V> getMap() {
        return mMap;
    }

    protected ParcelableHashMapWrapper(Parcel in) {
        this.keyClassName = in.readString();
        this.valueClassName = in.readString();
        int mapSize = in.readInt();
        this.mMap = new HashMap<>(mapSize);
        try {
            keyClass = Class.forName(keyClassName);
            valueClass = Class.forName(valueClassName);
            for (int i = 0; i < mapSize; i++) {
                K key = in.readParcelable(keyClass.getClassLoader());
                V value = in.readParcelable(valueClass.getClassLoader());
                this.mMap.put(key, value);
            }
        } catch (ClassNotFoundException e) {
            CLog.e("Parcel to map conversion failed; did not find both classes %s and %s", keyClassName, valueClassName);
        }
    }

    public static final Parcelable.Creator<ParcelableHashMapWrapper> CREATOR = new Parcelable.Creator<ParcelableHashMapWrapper>() {
        @Override
        public ParcelableHashMapWrapper createFromParcel(Parcel source) {
            return new ParcelableHashMapWrapper(source);
        }

        @Override
        public ParcelableHashMapWrapper[] newArray(int size) {
            return new ParcelableHashMapWrapper[size];
        }
    };
}
