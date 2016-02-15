package com.dreamfactory.kurtishu.pretty.view.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dreamfactory.kurtishu.pretty.R;
import com.dreamfactory.kurtishu.pretty.api.entity.ImageDetail;
import com.dreamfactory.kurtishu.pretty.event.NavigatorEvent;
import com.dreamfactory.kurtishu.pretty.view.base.ActivityPresenter;
import com.dreamfactory.kurtishu.pretty.view.delegate.ImageDetailDelegate;
import com.dreamfactory.kurtishu.pretty.view.task.ImageDetailTask;
import com.dreamfactory.kurtishu.pretty.view.task.base.ExecutableThread;
import com.orhanobut.logger.Logger;

import de.greenrobot.event.EventBus;

public class ImageDetailActivity extends ActivityPresenter<ImageDetailDelegate> {

    @Override
    protected Class<ImageDetailDelegate> getDeletageClass() {
        return ImageDetailDelegate.class;
    }

    @Override
    protected void afterResume() {
        super.afterResume();

        int id = getIntent().getExtras().getInt("id");
        Logger.i("id = " + id);
    }


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(NavigatorEvent event) {
        finish();
    }
}
