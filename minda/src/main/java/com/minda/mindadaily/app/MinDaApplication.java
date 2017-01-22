package com.minda.mindadaily.app;

import android.app.Application;

/**
 * Created by kun on 2017/1/18.
 */

public class MinDaApplication extends Application {
    private static MinDaApplication mInstance;

    public static MinDaApplication getInstance() {
        return mInstance;
    }
}
