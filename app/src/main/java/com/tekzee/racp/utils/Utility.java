package com.tekzee.racp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

    public static Context appContext;
    private static String RACPAPP = "RACPAPP";

    // for username string preferences
    public static void setSharedPreference(Context context, String name, String value) {
        appContext = context;
        SharedPreferences settings = appContext.getSharedPreferences(RACPAPP, 0);
        SharedPreferences.Editor editor = settings.edit();
        // editor.clear();
        editor.putString(name, value);
        editor.commit();
    }
    public static String getSharedPreferences(Context context, String name) {
        appContext = context;
        SharedPreferences settings = appContext.getSharedPreferences(RACPAPP, 0);
        return settings.getString(name, "");
    }



    // for username string preferences
    public static void setIntegerSharedPreference(Context context, String name, int value) {
        appContext = context;
        SharedPreferences settings = appContext.getSharedPreferences(RACPAPP, 0);
        SharedPreferences.Editor editor = settings.edit();
        // editor.clear();
        editor.putInt(name, value);
        editor.commit();
    }


    public static int getIngerSharedPreferences(Context context, String name) {
        SharedPreferences settings = context.getSharedPreferences(RACPAPP, 0);
        return settings.getInt(name, 0);
    }


    public static void setSharedPreferenceBoolean(Context context, String name, boolean value) {
        appContext = context;
        SharedPreferences settings = appContext.getSharedPreferences(RACPAPP, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(name, value);
        editor.commit();
    }

    public static boolean getSharedPreferencesBoolean(Context context, String name) {
        appContext = context;
        SharedPreferences settings = appContext.getSharedPreferences(RACPAPP, 0);
        return settings.getBoolean(name, false);
    }



    public static void removepreference(Context context) {
        SharedPreferences settings = context.getSharedPreferences(RACPAPP, 0);
        settings.edit().clear().commit();
    }



}
