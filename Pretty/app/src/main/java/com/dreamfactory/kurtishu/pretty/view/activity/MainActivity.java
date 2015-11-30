package com.dreamfactory.kurtishu.pretty.view.activity;

import android.os.Handler;
import android.os.Message;

import com.dreamfactory.kurtishu.pretty.api.ImageService;
import com.dreamfactory.kurtishu.pretty.api.entity.ImageList;
import com.dreamfactory.kurtishu.pretty.view.base.ActivityPresenter;
import com.dreamfactory.kurtishu.pretty.view.delegate.MainDelegate;
import com.dreamfactory.kurtishu.pretty.view.task.SearchImagesTask;
import com.dreamfactory.kurtishu.pretty.view.task.base.ExecutableThread;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.io.IOException;

import de.greenrobot.event.EventBus;

/**
 * Created by kurtishu on 11/30/15.
 */
public class MainActivity extends ActivityPresenter<MainDelegate> {

    @Override
    protected Class<MainDelegate> getDeletageClass() {
        return MainDelegate.class;
    }


    @Override
    protected void beforePause() {
        super.beforePause();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void afterResume() {
        super.afterResume();

        Fresco.initialize(this);
      new SearchImagesTask(mHandler, null).start();
    }

    @Override
    protected void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == ExecutableThread.EXECUTE_STATE_SUCCESS) {
                ((MainDelegate)viewDelegate).initViews(MainActivity.this, (ImageList) msg.obj);
            }

            super.handleMessage(msg);
        }
    };
}
