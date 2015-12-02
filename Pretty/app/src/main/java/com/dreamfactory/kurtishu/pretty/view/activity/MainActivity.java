package com.dreamfactory.kurtishu.pretty.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.dreamfactory.kurtishu.pretty.api.ImageService;
import com.dreamfactory.kurtishu.pretty.api.entity.ImageList;
import com.dreamfactory.kurtishu.pretty.api.entity.SearchEntity;
import com.dreamfactory.kurtishu.pretty.event.NavigatorEvent;
import com.dreamfactory.kurtishu.pretty.event.UpdateImageListEvent;
import com.dreamfactory.kurtishu.pretty.view.base.ActivityPresenter;
import com.dreamfactory.kurtishu.pretty.view.delegate.MainDelegate;
import com.dreamfactory.kurtishu.pretty.view.task.SearchImagesTask;
import com.dreamfactory.kurtishu.pretty.view.task.base.ExecutableThread;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.io.IOException;
import java.util.Map;

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
    protected void initViews() {
        super.initViews();
        EventBus.getDefault().registerSticky(this);
        ((MainDelegate) viewDelegate).initViews(MainActivity.this);
    }

    @Override
    protected void beforePause() {
        super.beforePause();
    }

    @Override
    protected void afterResume() {
        super.afterResume();
        Fresco.initialize(this);
        new SearchImagesTask(mHandler, null).start();
    }

    @Override
    protected void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == ExecutableThread.EXECUTE_STATE_SUCCESS) {
                ((MainDelegate)viewDelegate).setRecyclerViewAdapter((ImageList) msg.obj);
            }

            super.handleMessage(msg);
        }
    };

    public void onEventMainThread(final UpdateImageListEvent event) {
        if (event.isShouldReload()) {
            ((MainDelegate)viewDelegate).clearRecyclerViewData();
        }

        new SearchImagesTask(mHandler, event.getSearchEntity()).start();

    }

    public void onEventMainThread(NavigatorEvent event) {

        Map<String, ?> parmas = event.getParams();
        Intent intent = new Intent(this, ImageDetailActivity.class);
        intent.putExtra("id", (Integer)parmas.get("id"));
        intent.putExtra("img", (String)parmas.get("img"));
        startActivity(intent);
    }
}
