package com.dreamfactory.kurtishu.pretty.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.dreamfactory.kurtishu.pretty.R;
import com.dreamfactory.kurtishu.pretty.db.DBManager;
import com.dreamfactory.kurtishu.pretty.model.Galleryclass;
import com.dreamfactory.kurtishu.pretty.view.base.ActivityPresenter;
import com.dreamfactory.kurtishu.pretty.view.delegate.SplashDelegate;
import com.dreamfactory.kurtishu.pretty.view.task.ClasslfyTask;
import com.dreamfactory.kurtishu.pretty.view.task.base.ExecutableThread;

import java.util.List;

public class SplashActivity extends ActivityPresenter<SplashDelegate> {


    @Override
    protected Class<SplashDelegate> getDeletageClass() {
        return SplashDelegate.class;
    }

    @Override
    protected void afterResume() {
        super.afterResume();

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                new ClasslfyTask(mHandler).start();
            }
        }, 2000);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {


            if (msg.what == ExecutableThread.EXECUTE_STATE_SUCCESS) {
                DBManager.saveClasslfy((List<Galleryclass>) msg.obj);
            }
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            super.handleMessage(msg);
        }
    };
}
