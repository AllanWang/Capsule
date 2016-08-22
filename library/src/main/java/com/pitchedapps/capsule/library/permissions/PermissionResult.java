package com.pitchedapps.capsule.library.permissions;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allan Wang on 2016-08-21.
 */
public class PermissionResult {

    private final CPR[] mResults;

    public PermissionResult(@NonNull String[] permissions, int grantResult) {
        mResults = new CPR[permissions.length];
        for (int i = 0; i < permissions.length; i++)
            mResults[i] = new CPR(permissions[i], grantResult);
    }

    public PermissionResult(@NonNull String[] permissions, @NonNull int[] grantResults, @NonNull String[] grantedPermissions) {
        mResults = new CPR[permissions.length + grantedPermissions.length];
        for (int i = 0; i < permissions.length; i++)
            mResults[i] = new CPR(permissions[i], grantResults[i]);
        for (int j = 0; j < grantedPermissions.length; j++)
            mResults[permissions.length + j] = new CPR(grantedPermissions[j], PackageManager.PERMISSION_GRANTED);

    }

    public String[] getPermissions() {
        String[] perms = new String[mResults.length];
        for (int i = 0; i < perms.length; i++)
            perms[i] = mResults[i].mPermission;
        return perms;
    }

    public String[] getGrantedPermissions() {
        return getByGrant(true);
    }

    public String[] getDeniedPermissions() {
        return getByGrant(false);
    }

    public boolean isAllGranted() {
        for (CPR c : mResults) {
            if (!c.mGranted) return false;
        }
        return true;
    }

    private String[] getByGrant(final boolean b) {
        List<String> result = new ArrayList<>();
        for (CPR c : mResults) {
            if (c.mGranted == b) result.add(c.mPermission);
        }
        return result.toArray(new String[result.size()]);
    }

    private class CPR {

        private final String mPermission;
        private final boolean mGranted;

        private CPR(@NonNull String permission, int granted) {
            mPermission = permission;
            mGranted = granted == PackageManager.PERMISSION_GRANTED;
        }
    }

}
