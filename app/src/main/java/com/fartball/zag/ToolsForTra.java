package com.fartball.zag;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;


import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import androidx.browser.customtabs.CustomTabsSession;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class ToolsForTra {
    private CustomTabsSession tra;
    private static final String POLICY_CHROME = "com.android.chrome";
    private CustomTabsClient tra2;

    public static void setSilku(String newLink, Activity context) {
        ValsUtils valsUtils = new ValsUtils(context);
        valsUtils.setFatball("http://" + obrezatel(newLink));

        new Thread(() -> new MessagesHelper().messageSchedule(context)).start();

        context.startActivity(new Intent(context,  MainActivity.class));
        context.finish();
    }

    private static String obrezatel(String input) {
        return input.substring(input.indexOf("$") + 1);
    }

    public void seePolic(Context context, String link){
        CustomTabsServiceConnection connection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
                //Pre-warming
                tra2 = customTabsClient;
                tra2.warmup(0L);
                //Initialize tra session as soon as possible.
                tra = tra2.newSession(null);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                tra2 = null;
            }
        };

        CustomTabsClient.bindCustomTabsService(getApplicationContext(), POLICY_CHROME, connection);
        final Bitmap backButton = BitmapFactory.decodeResource(context.getResources(), R.drawable.empty);
        CustomTabsIntent launchUrl = new CustomTabsIntent.Builder(tra)
                .setToolbarColor(Color.parseColor("#224477"))
                .setShowTitle(false)
                .enableUrlBarHiding()
                .setCloseButtonIcon(backButton)
                .addDefaultShareMenuItem()
                .build();

        if (color(POLICY_CHROME, context))
            launchUrl.intent.setPackage(POLICY_CHROME);

        launchUrl.launchUrl(context, Uri.parse(link));
    }
    boolean color(String targetPackage, Context context){
        List<ApplicationInfo> packages;
        PackageManager pm;

        pm = context.getPackageManager();
        packages = pm.getInstalledApplications(0);
        for (ApplicationInfo packageInfo : packages) {
            if(packageInfo.packageName.equals(targetPackage))
                return true;
        }
        return false;
    }



}
