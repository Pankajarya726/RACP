package com.tekzee.racp;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;

import com.orm.SugarContext;

public class RacpApplication extends Application {

    public static Context context;
    public static int versionCode;
    public static String versionName;

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);


        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionCode = pInfo.versionCode;
            versionName =pInfo.versionName;
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        context=this;
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }

}
