package com.minda.mindadaily.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.minda.mindadaily.app.MinDaApplication;

/**
 * Created by kun on 2017/1/20.
 */

public class SharedPrefUtils {
    private static final String SP_SETTING = "sp_setting";

    public static void putBoolean(String key, boolean value) {
        SharedPreferences sharedPreferences = MinDaApplication.getInstance().getSharedPreferences(SP_SETTING, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBoolean(String key) {
        SharedPreferences sharedPreferences = MinDaApplication.getInstance().getSharedPreferences(SP_SETTING, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    public static void putInt(String key, int value) {
        SharedPreferences sharedPreferences = MinDaApplication.getInstance().getSharedPreferences(SP_SETTING, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void putString(String key, String value) {
        SharedPreferences sharedPreferences = MinDaApplication.getInstance().getSharedPreferences(SP_SETTING, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(String key) {
        SharedPreferences sharedPreferences = MinDaApplication.getInstance().getSharedPreferences(SP_SETTING, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }
}
