package com.pitchedapps.capsule.library.item;

import android.os.Parcel;
import android.os.Parcelable;

import com.pitchedapps.capsule.library.logging.CLog;

import java.util.HashMap;

/**
 * Created by Allan Wang on 2016-12-28.
 * <p>
 * Wrapper for sending hashmaps through parcels
 * Relies on getting class through string
 */

public class ParcelableMapWrapper<K extends Parcelable, V extends Parcelable> implements Parcelable {
    private HashMap<K, V> mMap;
    private transient Class keyClass, valueClass;
    private String keyClassName, valueClassName;

    public ParcelableMapWrapper(HashMap<K, V> map, Class<K> keyClass, Class<V> valueClass) {
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
        dest.writeInt(this.mMap.size());
        for (HashMap.Entry<K, V> entry : this.mMap.entrySet()) {
            dest.writeParcelable(entry.getKey(), flags);
            dest.writeParcelable(entry.getValue(), flags);
        }
        dest.writeString(this.keyClassName);
        dest.writeString(this.valueClassName);
    }

    public HashMap<K, V> getMap() {
        return mMap;
    }

    protected ParcelableMapWrapper(Parcel in) {
        int mapSize = in.readInt();
        this.mMap = new HashMap<K, V>(mapSize);
        this.keyClassName = in.readString();
        this.valueClassName = in.readString();
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

    public static final Parcelable.Creator<ParcelableMapWrapper> CREATOR = new Parcelable.Creator<ParcelableMapWrapper>() {
        @Override
        public ParcelableMapWrapper createFromParcel(Parcel source) {
            return new ParcelableMapWrapper(source);
        }

        @Override
        public ParcelableMapWrapper[] newArray(int size) {
            return new ParcelableMapWrapper[size];
        }
    };
}
