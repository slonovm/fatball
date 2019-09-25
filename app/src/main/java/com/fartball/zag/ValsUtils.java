package com.fartball.zag;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.applinks.AppLinkData;

public class ValsUtils {
    private static String data = "data";
    private SharedPreferences preferences;

    public ValsUtils(Context context){
        String NAME = "data";
        preferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public void setData(String data){
        preferences.edit().putString(ValsUtils.data, data).apply();
    }

    public String getData(){
        return preferences.getString(data, "");
    }

    public static void init(Activity context){
        AppLinkData.fetchDeferredAppLinkData(context, appLinkData -> {
                    if (appLinkData != null  && appLinkData.getTargetUri() != null) {
                        if (appLinkData.getArgumentBundle().get("target_url") != null) {
                            String link = appLinkData.getArgumentBundle().get("target_url").toString();
                            Utils.setData(link, context);
                        }
                    }
                }
        );
    }
}
