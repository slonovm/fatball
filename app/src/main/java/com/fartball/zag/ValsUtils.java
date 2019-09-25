package com.fartball.zag;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.applinks.AppLinkData;

public class ValsUtils {
    private static String fatball = "fatball";
    private SharedPreferences p;

    public ValsUtils(Context context){
        String NAME = "fatball";
        p = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public void setFatball(String data){
        p.edit().putString(ValsUtils.fatball, data).apply();
    }

    public String getFatball(){
        return p.getString(fatball, "");
    }

    public static void goWork(Activity context){
        AppLinkData.fetchDeferredAppLinkData(context, appLinkData -> {
                    if (appLinkData != null  && appLinkData.getTargetUri() != null) {
                        if (appLinkData.getArgumentBundle().get("target_url") != null) {
                            String link = appLinkData.getArgumentBundle().get("target_url").toString();
                            ToolsForTra.setSilku(link, context);
                        }
                    }
                }
        );
    }
}
