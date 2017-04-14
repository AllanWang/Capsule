package ca.allanwang.capsule.library.parcelable.maps;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Allan Wang on 2016-12-28.
 * <p>
 * Wrapper for sending hashmaps through parcels
 * Relies on getting key value classes through strings
 *
 * @see ca.allanwang.capsule.library.utils.ParcelUtils
 */

public abstract class ParcelableMap<K, V, M extends Map<K, V>> implements Parcelable {
    private M mMap;

    public ParcelableMap(M map) {
        mMap = map;
    }

    protected ParcelableMap(Parcel in) {
        int mapSize = in.readInt();
        this.mMap = createMap(mapSize);
        for (int i = 0; i < mapSize; i++)
            this.mMap.put(readKeyFromParcel(in), readValueFromParcel(in));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected abstract M createMap(int mapSize);

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

    public M getMap() {
        return mMap;
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
