package com.dreamfactory.kurtishu.pretty.view.base;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by kurtishu on 11/30/15.
 */
public abstract class ActivityPresenter<T extends IDelegate> extends Activity {

    protected T viewDelegate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            viewDelegate = getDeletageClass().newInstance();
            viewDelegate.create(getLayoutInflater(), null, savedInstanceState);
            setContentView(viewDelegate.getRootView());
            bindListener();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected final void onPause() {
        beforePause();
        super.onPause();
    }

    protected void beforePause(){}

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

    protected void bindListener(){};
}
