package ca.allanwang.capsule.library.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

/**
 * Created by Allan Wang on 2016-12-21.
 * <p>
 * Collection of general helpers
 */
public class Utils {

    public static boolean isKitkatPlus() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean isLollipopPlus() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    /**
     * Returns app version name
     *
     * @param context app context
     * @return version name
     */
    public static String getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "Unknown"; // this should never happen
        }
    }

    public static String getAppPackageName(Context context) {
        return context.getPackageName();
    }

    public static boolean hasNetwork(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    /**
     * Checks if a given package is installed
     *
     * @param context     app context
     * @param packageName packageId
     * @return true if installed with activity, false otherwise
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        final PackageManager pm = context.getPackageManager();
        boolean installed;
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }
}
