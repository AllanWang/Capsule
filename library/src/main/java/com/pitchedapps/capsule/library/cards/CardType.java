package com.pitchedapps.capsule.library.cards;

import android.support.annotation.LayoutRes;

/**
 * Created by Allan Wang on 2016-08-24.
 */
public enum CardType {

    BASIC(),
    DIVIDER();


    private int layoutId;

    CardType() {
        //empty constructor
    }

    CardType(@LayoutRes int id) {
        layoutId = id;
    }

}
