package com.dreamfactory.kurtishu.pretty;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.logger.Logger;

/**
 * Created by kurtishu on 11/30/15.
 */
public class PrettyApp extends Application {

    private static PrettyApp instance;

    @Override
    public void onCreate() {
        super.onCreate();

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().penaltyDialog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());

        Logger.init();
        Fresco.initialize(this);
        instance = this;
    }

    public static Context getContext() {
        return instance;
    }
}
