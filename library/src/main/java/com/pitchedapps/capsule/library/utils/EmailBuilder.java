package com.pitchedapps.capsule.library.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.pitchedapps.capsule.library.R;
import com.pitchedapps.capsule.library.logging.CLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allan Wang on 2016-12-21.
 * <p>
 * Simple Email Builder
 */

public class EmailBuilder {

    private int emailId, emailSubject;
    private boolean deviceDetails = false;
    private String message = "Write here.";
    private List<Package> packageList = new ArrayList<>();

    public EmailBuilder(@StringRes int emailId, @StringRes int emailSubject) {
        this.emailId = emailId;
        this.emailSubject = emailSubject;
    }

    public EmailBuilder addMessage(@NonNull String s) {
        message = s;
        return this;
    }

    public EmailBuilder addDeviceDetails() {
        deviceDetails = true;
        return this;
    }

    public EmailBuilder isPackageInstalled(String packageName, String appName) {
        packageList.add(new Package(packageName, appName));
        return this;
    }

    private static class Package {
        String packageName;
        String appName;

        Package(String p, String a) {
            packageName = p;
            appName = a;
        }
    }

    public void create(@NonNull Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + context.getResources().getString(emailId)));
        intent.putExtra(Intent.EXTRA_SUBJECT, context.getResources().getString(emailSubject));
        StringBuilder emailBuilder = new StringBuilder();
        emailBuilder.append(message).append("\n\n");
        if (deviceDetails) {
            emailBuilder.append("\nOS Version: ").append(System.getProperty("os.version")).append("(").append(Build.VERSION.INCREMENTAL).append(")")
                    .append("\nOS API Level: ").append(Build.VERSION.SDK_INT)
                    .append("\nDevice: ").append(Build.DEVICE)
                    .append("\nManufacturer: ").append(Build.MANUFACTURER)
                    .append("\nModel (and Product): ").append(Build.MODEL).append(" (").append(Build.PRODUCT).append(")")
                    .append("\n");
        }

        try {
            PackageInfo appInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            emailBuilder.append("\nApp Version Name: ").append(appInfo.versionName)
                    .append("\nApp Version Code: ").append(appInfo.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            CLog.e("EmailBuilder packageInfo not found");
        }

        for (Package p : packageList) {
            if (Utils.isAppInstalled(context, p.packageName)) {
                emailBuilder.append(String.format("\n%s is installed", p.appName));
            }
        }
        intent.putExtra(Intent.EXTRA_TEXT, emailBuilder.toString());
        context.startActivity(Intent.createChooser(intent, (context.getResources().getString(R.string.send_title))));
    }
}
