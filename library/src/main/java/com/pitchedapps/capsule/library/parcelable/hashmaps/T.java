package com.pitchedapps.capsule.library.parcelable.hashmaps;

import android.os.Parcel;

/**
 * Created by Allan Wang on 2016-12-28.
 */

public class T extends ParcelableHashMap<String, Integer> {
    protected T(Parcel in) {
        super(in);
    }

    @Override
    protected void writeKeyToParcel(Parcel dest, String key, int flags) {
        
    }

    @Override
    protected void writeValueToParcel(Parcel dest, Integer value, int flags) {

    }

    @Override
    protected String readKeyFromParcel(Parcel in) {
        return null;
    }

    @Override
    protected Integer readValueFromParcel(Parcel in) {
        return null;
    }

    public static final Creator<T> CREATOR = new Creator<T>() {
        @Override
        public T createFromParcel(Parcel source) {
            return new T(source);
        }

        @Override
        public T[] newArray(int size) {
            return new T[size];
        }
    };
}
