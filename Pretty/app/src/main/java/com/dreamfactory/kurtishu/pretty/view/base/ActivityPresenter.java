package com.dreamfactory.kurtishu.pretty.view.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dreamfactory.kurtishu.pretty.R;
import com.dreamfactory.kurtishu.pretty.utils.StatusBarCompat;
import com.orhanobut.logger.Logger;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by kurtishu on 11/30/15.
 */
public abstract class ActivityPresenter<T extends IDelegate> extends AppCompatActivity {

    private T viewDelegate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            viewDelegate = getDeletageClass().newInstance();
            viewDelegate.create(getLayoutInflater(), null, savedInstanceState);
            setContentView(viewDelegate.getRootView());
            StatusBarCompat.compat(this, getResources().getColor(R.color.theme_color));
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

    public void onResume() {
        super.onResume();
        afterResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
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
