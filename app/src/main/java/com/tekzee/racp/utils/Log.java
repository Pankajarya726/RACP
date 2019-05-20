package com.tekzee.racp.utils;

import com.tekzee.racp.BuildConfig;

public final class Log {

    public static final void view(String TAG,String message){
        if(BuildConfig.DEBUG){

            android.util.Log.e(TAG,message);

        }
    }
}
