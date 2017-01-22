package com.minda.mindadaily.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by kun on 2017/1/19.
 */

public class ToastUtils {
    public static void toast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void toast(Context context, int id) {
        Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
    }
}
