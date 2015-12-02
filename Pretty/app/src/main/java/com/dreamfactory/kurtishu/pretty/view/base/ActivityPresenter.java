package com.dreamfactory.kurtishu.pretty.view.base;

import android.app.Activity;
import android.os.Bundle;

import com.orhanobut.logger.Logger;

/**
 * Created by kurtishu on 11/30/15.
 */
public abstract class ActivityPresenter<T extends IDelegate> extends Activity {

    private T viewDelegate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            viewDelegate = getDeletageClass().newInstance();
            viewDelegate.create(getLayoutInflater(), null, savedInstanceState);
            setContentView(viewDelegate.getRootView());
            viewDelegate.initViewControllers(this, getIntent());
        } catch (InstantiationException e) {
            Logger.e("InstantiationException" + e.getMessage());
        } catch (IllegalAccessException e) {
            Logger.e("IllegalAccessException" + e.getMessage());
        }
        afterCreate();
    }

    protected T getViewDelegate() {
        return viewDelegate;
    }

    protected void afterCreate(){}

    @Override
    protected final void onResume() {
        super.onResume();
        afterResume();
    }

    protected void afterResume(){}


    @Override
    protected final void onDestroy() {
        onDestroyView();
        viewDelegate = null;
        super.onDestroy();
    }

    protected void onDestroyView(){};

    @Override
    public final void onBackPressed() {
        if(!handleBackPressed()) {
            super.onBackPressed();
        }
    }

    public boolean handleBackPressed() {
        return false;
    }

    protected abstract Class<T> getDeletageClass();
}
