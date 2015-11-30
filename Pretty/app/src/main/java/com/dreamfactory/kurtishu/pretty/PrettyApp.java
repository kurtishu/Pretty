package com.dreamfactory.kurtishu.pretty;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * Created by kurtishu on 11/30/15.
 */
public class PrettyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Logger.init();
    }
}
