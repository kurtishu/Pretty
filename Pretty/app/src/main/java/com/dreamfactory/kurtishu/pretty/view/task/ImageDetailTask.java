package com.dreamfactory.kurtishu.pretty.view.task;

import android.os.Handler;
import android.os.Message;

import com.dreamfactory.kurtishu.pretty.api.ImageService;
import com.dreamfactory.kurtishu.pretty.api.entity.ImageDetail;
import com.dreamfactory.kurtishu.pretty.view.task.base.ExecutableThread;

/**
 * Created by kurtishu on 12/1/15.
 */
public class ImageDetailTask extends ExecutableThread {

    private Handler mHandler;
    private int id;
    public ImageDetailTask(Handler mHandler, int id) {
        this.mHandler = mHandler;
        this.id = id;
    }

    @Override
    public void runBackground() {
        try {
            ImageDetail imageDetail = new ImageService().getImageDetail(id);
            setPostMessage(EXECUTE_STATE_SUCCESS, imageDetail);
        } catch (Exception e) {
            setPostMessage(EXECUTE_STATE_FAILURE, e.getMessage());
        }
    }

    @Override
    public void postSuccessToUI(Message msg) {
        mHandler.sendMessage(msg);
    }

    @Override
    public void postErrorToUI(String errorMsg) {
        mHandler.sendEmptyMessage(EXECUTE_STATE_FAILURE);
    }
}
