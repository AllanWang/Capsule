package com.pitchedapps.capsule.library.item;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.pitchedapps.capsule.library.logging.CLog;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Allan Wang on 2016-12-28.
 * <p>
 * Wrapper for sending hashmaps through parcels
 * Relies on getting key value classes through strings
 *
 * @see com.pitchedapps.capsule.library.utils.ParcelUtils
 */

public class ParcelableHashMapWrapperOld<K, V> implements Parcelable {
    private HashMap<K, V> mMap;
    private transient Class keyClass, valueClass;
    private Type keyType, valueType;

    private enum Type {
        STRING, INTEGER, FLOAT, LONG, DOUBLE, PARCELABLE, PARCELABLE_ARRAY, ARRAYLIST, MAP;
//        STRING(String.class.getName()),
//        INTEGER(int.class.getName()),
//        FLOAT(float.class.getName()), LONG,
//        DOUBLE(double.class.getName()),
//        PARCELABLE(Parcelable.class.getName()),
//        PARCELABLE_ARRAY(Parcelable[].class.getName()),
//        LIST(List.class.getName()),
//        MAP(Map.class.getName());
//
//        private String className;
//
//        private Types(String s) {
//            className = s;
//        }
//
//        private String getClassName() {
//            return className;
//        }
    }

    public ParcelableHashMapWrapperOld(HashMap<K, V> map, Class<K> keyClass, Class<V> valueClass) {
        this.mMap = map;
        this.keyClass = keyClass;
        this.valueClass = valueClass;
        this.keyType = getClassType(keyClass);
        this.valueType = getClassType(valueClass);
    }

    private ParcelableHashMapWrapperOld(HashMap<K, V> map) {
        if (map == null || map.isEmpty())
            throw new IllegalArgumentException("Please make sure ParcelableHashMap has at least one entry; otherwise use full constructor with classes");
        Iterator<Map.Entry> iterator = map.entrySet().iterator();
        Map.Entry entry = iterator.next();
        keyClass = entry.getKey().getClass();
        while (entry.getKey() == null || entry.getValue() == null) {
            if (!iterator.hasNext()) break;
            entry = iterator.next();
        }
        if (entry.getKey() != null) {
            keyClass = entry.getKey().getClass();
            keyType = getClassType(keyClass);
        } else
            throw new IllegalArgumentException("Ambiguous ParcelableHashMap format: no nonnull keys found\n\tPlease use explicit constructor with classes");
        if (entry.getValue() != null) {
            valueClass = entry.getValue().getClass();
            valueType = getClassType(valueClass);
        } else
            throw new IllegalArgumentException("Ambiguous ParcelableHashMap format: no nonnull keys found\n\tPlease use explicit constructor with classes");

    }

    private Type getClassType(Class c) {
        if (Parcelable.class.isAssignableFrom(c)) return Type.PARCELABLE;
        else if (String.class.isAssignableFrom(c)) return Type.STRING;
        else if (Integer.class.isAssignableFrom(c)) return Type.INTEGER;
        else if (Double.class.isAssignableFrom(c)) return Type.DOUBLE;
        else if (Float.class.isAssignableFrom(c)) return Type.FLOAT;
        else if (Long.class.isAssignableFrom(c)) return Type.LONG;
        else if (Parcelable[].class.isAssignableFrom(c)) return Type.PARCELABLE_ARRAY;
        else if (List.class.isAssignableFrom(c)) return Type.LIST;
        else if (Map.class.isAssignableFrom(c)) return Type.MAP;
        else
            throw new IllegalArgumentException("Illegal type in ParcelableHashMap; please see the valid type enums");
    }

    private void writeToParcel(Parcel dest, Type classType, Object value, int flags) {
        switch (classType) {
            case PARCELABLE:
                dest.writeParcelable((Parcelable) value, flags);
                break;
            case STRING:
                dest.writeString(String.valueOf(value));
                break;
            case INTEGER:
                dest.writeInt((int) value);
                break;
            case DOUBLE:
                dest.writeDouble((double) value);
                break;
            case FLOAT:
                dest.writeFloat((float) value);
                break;
            case LONG:
                dest.writeLong((long) value);
                break;
            case PARCELABLE_ARRAY:
                dest.writeParcelableArray((Parcelable[]) value, flags);
                break;
            case ARRAYLIST:
                dest.writeList((List) value);
                break;
            case MAP:
                if (((Map) value).isEmpty())
                    throw new IllegalArgumentException("Please make sure inner children of a ParcelableHashMap has at least one entry; otherwise set them to null");
                HashMap hashMap = mapToHashMap((Map) value);
                new ParcelableHashMapWrapperOld(hashMap).writeToParcel(dest, flags);
                break;
        }
    }

    private Object readFromParcel(Parcel in, Type classType, @Nullable ClassLoader classLoader) {
        switch (classType) {
            case PARCELABLE:
                return in.readParcelable(classLoader);
            break;
            case STRING:
                in.readParcelable(classLoader);
                break;
            case INTEGER:
                in.readInt();
                break;
            case DOUBLE:
                in.readDouble();
                break;
            case FLOAT:
                in.readFloat();
                break;
            case LONG:
                in.readLong();
                break;
            case PARCELABLE_ARRAY:
                in.readParcelableArray(classLoader);
                break;
            case ARRAYLIST:
                in.readArrayList(classLoader);
                break;
            case MAP:
                in.readHashMap()
                if (((Map) value).isEmpty())
                    throw new IllegalArgumentException("Please make sure inner children of a ParcelableHashMap has at least one entry; otherwise set them to null");
                HashMap hashMap = mapToHashMap((Map) value);
                new ParcelableHashMapWrapperOld(hashMap).writeToParcel(dest, flags);
                break;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.keyType);
        dest.writeSerializable(this.valueType);
        dest.writeInt(this.mMap.size());
        for (HashMap.Entry<K, V> entry : this.mMap.entrySet()) {
            writeToParcel(dest, keyType, entry.getKey(), flags);
            writeToParcel(dest, valueType, entry.getValue(), flags);
        }
    }

    private Type getClassType(Object o) {
        if (o == null) return Type.PARCELABLE;
        return getClassType(o.getClass());
    }



    private <X, Y> HashMap<X, Y> mapToHashMap(Map<X, Y> map) {
        if (map instanceof HashMap) return (HashMap<X, Y>) map;
        return new HashMap<X, Y>(map);
    }

    public HashMap<K, V> getMap() {
        return mMap;
    }

    protected ParcelableHashMapWrapperOld(Parcel in) {
        this.keyType = (Type) in.readSerializable();
        this.valueType = (Type) in.readSerializable();
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

    public static final Creator<ParcelableHashMapWrapperOld> CREATOR = new Creator<ParcelableHashMapWrapperOld>() {
        @Override
        public ParcelableHashMapWrapperOld createFromParcel(Parcel source) {
            return new ParcelableHashMapWrapperOld(source);
        }

        @Override
        public ParcelableHashMapWrapperOld[] newArray(int size) {
            return new ParcelableHashMapWrapperOld[size];
        }
    };
}
