package com.dreamfactory.kurtishu.pretty.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;

import com.dreamfactory.kurtishu.pretty.R;
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
    protected void afterCreate() {
        super.afterCreate();
        EventBus.getDefault().registerSticky(this);
        new SearchImagesTask(mHandler, null).start();
    }

    @Override
    protected void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == ExecutableThread.EXECUTE_STATE_SUCCESS) {
                if (null != msg.obj && null != getViewDelegate()) {
                    getViewDelegate().setRecyclerViewAdapter((ImageList) msg.obj);
                }
            }

            super.handleMessage(msg);
        }
    };

    public void onEventMainThread(final UpdateImageListEvent event) {
        if (event.isShouldReload()) {
            getViewDelegate().clearRecyclerViewData();
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
