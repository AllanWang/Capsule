package com.pitchedapps.capsule.library.interfaces;

import com.mikepenz.iconics.typeface.IIcon;

/**
 * Created by Allan Wang on 2016-10-30.
 */

public interface CFab {

    IIcon getFabIcon();

    /**
     * Defines the visibility of the fab
     * true will show it
     * false will hide it
     * null will not modify the state
     *
     * @return fab existence
     */
    Boolean hasFab();

}
